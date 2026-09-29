package com.example.data.repository

import com.example.data.local.CourseContentProvider
import com.example.data.local.UserDao
import com.example.data.model.BookmarkEntity
import com.example.data.model.CourseModule
import com.example.data.model.GuidedProject
import com.example.data.model.HtmlTagInfo
import com.example.data.model.Lesson
import com.example.data.model.LessonProgressEntity
import com.example.data.model.NoteEntity
import com.example.data.model.PracticeChallenge
import com.example.data.model.PracticeRecordEntity
import com.example.data.model.ProjectSubmissionEntity
import com.example.data.model.UserProfileEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LearnHtmlRepository(private val userDao: UserDao) {

    val userProfile: Flow<UserProfileEntity?> = userDao.getUserProfile()
    val allProgress: Flow<List<LessonProgressEntity>> = userDao.getAllLessonProgress()
    val allNotes: Flow<List<NoteEntity>> = userDao.getAllNotes()
    val allBookmarks: Flow<List<BookmarkEntity>> = userDao.getAllBookmarks()
    val allProjects: Flow<List<ProjectSubmissionEntity>> = userDao.getAllProjectSubmissions()
    val allPracticeRecords: Flow<List<PracticeRecordEntity>> = userDao.getAllPracticeRecords()
    val completedCount: Flow<Int> = userDao.getCompletedLessonCount()

    val totalLessonsCount: Int = CourseContentProvider.modules.sumOf { it.lessons.size }

    fun getModules(): List<CourseModule> = CourseContentProvider.modules

    fun getLessonById(id: String): Lesson? {
        for (module in CourseContentProvider.modules) {
            val found = module.lessons.find { it.id == id }
            if (found != null) return found
        }
        return null
    }

    fun getGuidedProjects(): List<GuidedProject> = CourseContentProvider.guidedProjects

    fun getHtmlTags(): List<HtmlTagInfo> = CourseContentProvider.htmlTags

    fun getPracticeChallenges(): List<PracticeChallenge> = CourseContentProvider.practiceChallenges

    suspend fun saveProfile(profile: UserProfileEntity) {
        userDao.insertOrUpdateProfile(profile)
    }

    suspend fun markLessonCompleted(lessonId: String, moduleId: Int, userCode: String? = null) {
        val progress = LessonProgressEntity(
            lessonId = lessonId,
            moduleId = moduleId,
            isCompleted = true,
            completedAt = System.currentTimeMillis(),
            userSavedCode = userCode
        )
        userDao.setLessonProgress(progress)
    }

    fun isLessonCompleted(lessonId: String): Flow<Boolean> {
        return userDao.getLessonProgress(lessonId).map { it?.isCompleted == true }
    }

    suspend fun addOrUpdateNote(lessonId: String, lessonTitle: String, title: String, content: String, noteId: Long = 0) {
        val note = NoteEntity(
            id = noteId,
            lessonId = lessonId,
            lessonTitle = lessonTitle,
            title = title,
            content = content,
            updatedAt = System.currentTimeMillis()
        )
        userDao.insertNote(note)
    }

    suspend fun deleteNote(noteId: Long) {
        userDao.deleteNote(noteId)
    }

    suspend fun toggleBookmark(id: String, itemType: String, title: String, subtitle: String, isCurrentlyBookmarked: Boolean) {
        if (isCurrentlyBookmarked) {
            userDao.removeBookmark(id)
        } else {
            userDao.addBookmark(
                BookmarkEntity(
                    id = id,
                    itemType = itemType,
                    title = title,
                    subtitle = subtitle
                )
            )
        }
    }

    fun isBookmarked(id: String): Flow<Boolean> = userDao.isBookmarked(id)

    suspend fun saveProjectCode(projectId: String, code: String, isCompleted: Boolean) {
        userDao.saveProjectSubmission(
            ProjectSubmissionEntity(
                projectId = projectId,
                userHtml = code,
                isCompleted = isCompleted,
                lastEdited = System.currentTimeMillis()
            )
        )
    }

    suspend fun recordPracticeAttempt(challengeId: String, isSuccess: Boolean) {
        userDao.savePracticeRecord(
            PracticeRecordEntity(
                challengeId = challengeId,
                isCompleted = isSuccess,
                attempts = 1,
                lastAttemptAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun resetAllData() {
        userDao.clearProgress()
        userDao.clearProjects()
        userDao.clearPractice()
    }
}
