package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.CourseContentProvider
import com.example.data.model.AchievementBadge
import com.example.data.model.BookmarkEntity
import com.example.data.model.CourseModule
import com.example.data.model.GlossaryItem
import com.example.data.model.GuidedProject
import com.example.data.model.HtmlTagInfo
import com.example.data.model.Lesson
import com.example.data.model.LessonProgressEntity
import com.example.data.model.NoteEntity
import com.example.data.model.PracticeChallenge
import com.example.data.model.PracticeRecordEntity
import com.example.data.model.ProjectSubmissionEntity
import com.example.data.model.UserProfileEntity
import com.example.data.repository.LearnHtmlRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class ScreenDestination {
    object Welcome : ScreenDestination()
    object OnboardingName : ScreenDestination()
    object OnboardingGoal : ScreenDestination()
    object OnboardingExperience : ScreenDestination()
    object PersonalizedStart : ScreenDestination()
    object MainNavigation : ScreenDestination()
    data class LessonDetail(val lessonId: String) : ScreenDestination()
    data class ProjectDetail(val projectId: String) : ScreenDestination()
    data class TagDetail(val tag: String) : ScreenDestination()
    object Search : ScreenDestination()
    object CelebrationCertificate : ScreenDestination()
    object AboutCreator : ScreenDestination()
    object Settings : ScreenDestination()
}

enum class MainNavTab {
    HOME,
    LEARN,
    PRACTICE,
    PROJECTS,
    REFERENCE,
    PROGRESS,
    PROFILE
}

class LearnHtmlViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LearnHtmlRepository

    init {
        val db = AppDatabase.getDatabase(application)
        repository = LearnHtmlRepository(db.userDao())
    }

    val userProfile: StateFlow<UserProfileEntity?> = repository.userProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val allLessonProgress: StateFlow<List<LessonProgressEntity>> = repository.allProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allNotes: StateFlow<List<NoteEntity>> = repository.allNotes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allBookmarks: StateFlow<List<BookmarkEntity>> = repository.allBookmarks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allProjects: StateFlow<List<ProjectSubmissionEntity>> = repository.allProjects
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allPracticeRecords: StateFlow<List<PracticeRecordEntity>> = repository.allPracticeRecords
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getLessonById(id: String): Lesson? = repository.getLessonById(id)

    fun recordPracticeAttempt(challengeId: String, isSuccess: Boolean) {
        viewModelScope.launch {
            repository.recordPracticeAttempt(challengeId, isSuccess)
        }
    }

    // Navigation state
    private val _currentScreen = MutableStateFlow<ScreenDestination>(ScreenDestination.Welcome)
    val currentScreen: StateFlow<ScreenDestination> = _currentScreen.asStateFlow()

    private val _currentTab = MutableStateFlow(MainNavTab.HOME)
    val currentTab: StateFlow<MainNavTab> = _currentTab.asStateFlow()

    // Temporary Onboarding State
    val onboardingName = MutableStateFlow("")
    val onboardingGoals = MutableStateFlow<Set<String>>(emptySet())
    val onboardingExperience = MutableStateFlow("Complete Beginner")

    // Playground Interactive Code State
    val playgroundCode = MutableStateFlow(
        "<!DOCTYPE html>\n<html>\n<head>\n  <title>Playground</title>\n</head>\n<body>\n  <h1>Hello, HTML Learner!</h1>\n  <p>Type your code here and see it live.</p>\n  <button onclick=\"alert('It works!')\">Click Me</button>\n</body>\n</html>"
    )

    // Search query
    val searchQuery = MutableStateFlow("")

    val modules: List<CourseModule> = repository.getModules()
    val totalLessonsCount: Int = repository.totalLessonsCount
    val guidedProjects: List<GuidedProject> = repository.getGuidedProjects()
    val htmlTags: List<HtmlTagInfo> = repository.getHtmlTags()
    val practiceChallenges: List<PracticeChallenge> = repository.getPracticeChallenges()
    val glossary: List<GlossaryItem> = CourseContentProvider.glossary
    val cheatSheet = CourseContentProvider.cheatSheet

    fun navigateTo(destination: ScreenDestination) {
        _currentScreen.value = destination
    }

    fun setTab(tab: MainNavTab) {
        _currentTab.value = tab
        _currentScreen.value = ScreenDestination.MainNavigation
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            val profile = UserProfileEntity(
                id = 1,
                name = onboardingName.value.ifBlank { "Learner" },
                learningGoals = onboardingGoals.value.joinToString(", "),
                experienceLevel = onboardingExperience.value,
                isOnboardingCompleted = true,
                streakDays = 1,
                appTheme = "DEVELOPER_DARK"
            )
            repository.saveProfile(profile)
            _currentScreen.value = ScreenDestination.PersonalizedStart
        }
    }

    fun completeLesson(lessonId: String, moduleId: Int) {
        viewModelScope.launch {
            repository.markLessonCompleted(lessonId, moduleId)
            // If all completed, can navigate to celebration
            val completedCount = (allLessonProgress.value.count { it.isCompleted } + 1)
            if (completedCount >= totalLessonsCount) {
                _currentScreen.value = ScreenDestination.CelebrationCertificate
            }
        }
    }

    fun toggleBookmark(id: String, type: String, title: String, subtitle: String) {
        viewModelScope.launch {
            val isBookmarked = allBookmarks.value.any { it.id == id }
            repository.toggleBookmark(id, type, title, subtitle, isBookmarked)
        }
    }

    fun isBookmarked(id: String): Boolean {
        return allBookmarks.value.any { it.id == id }
    }

    fun saveNote(lessonId: String, lessonTitle: String, title: String, content: String, noteId: Long = 0) {
        viewModelScope.launch {
            repository.addOrUpdateNote(lessonId, lessonTitle, title, content, noteId)
        }
    }

    fun deleteNote(noteId: Long) {
        viewModelScope.launch {
            repository.deleteNote(noteId)
        }
    }

    fun saveProject(projectId: String, code: String, isCompleted: Boolean) {
        viewModelScope.launch {
            repository.saveProjectCode(projectId, code, isCompleted)
        }
    }

    fun updateTheme(themeName: String) {
        viewModelScope.launch {
            val current = userProfile.value ?: UserProfileEntity()
            repository.saveProfile(current.copy(appTheme = themeName))
        }
    }

    fun resetProgress() {
        viewModelScope.launch {
            repository.resetAllData()
        }
    }

    fun getUnlockedAchievements(): List<AchievementBadge> {
        val completedCount = allLessonProgress.value.count { it.isCompleted }
        val projectsCount = allProjects.value.count { it.isCompleted }

        return CourseContentProvider.achievements.map { badge ->
            val unlocked = when (badge.id) {
                "first_step" -> completedCount >= 1
                "html_explorer" -> completedCount >= 5
                "tag_master" -> completedCount >= 10
                "form_builder" -> allLessonProgress.value.any { it.moduleId == 7 && it.isCompleted }
                "project_builder" -> projectsCount >= 1
                "html_complete" -> completedCount >= totalLessonsCount
                else -> false
            }
            badge.copy(isUnlocked = unlocked)
        }
    }
}
