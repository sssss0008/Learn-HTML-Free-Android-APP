package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val name: String = "",
    val learningGoals: String = "", // Comma-separated or serialized
    val experienceLevel: String = "Complete Beginner",
    val isOnboardingCompleted: Boolean = false,
    val streakDays: Int = 1,
    val lastActiveDate: Long = System.currentTimeMillis(),
    val appTheme: String = "DEVELOPER_DARK", // "SYSTEM", "LIGHT", "DEVELOPER_DARK", "AMOLED", "HIGH_CONTRAST"
    val codeFontSize: Int = 14,
    val isSoundEnabled: Boolean = true
)

@Entity(tableName = "lesson_progress")
data class LessonProgressEntity(
    @PrimaryKey val lessonId: String,
    val moduleId: Int,
    val isCompleted: Boolean = false,
    val completedAt: Long = 0L,
    val userSavedCode: String? = null
)

@Entity(tableName = "user_notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val lessonId: String,
    val lessonTitle: String,
    val title: String,
    val content: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_bookmarks")
data class BookmarkEntity(
    @PrimaryKey val id: String, // e.g. "lesson_m1_l1" or "tag_h1"
    val itemType: String, // "LESSON", "TAG", "PROJECT", "EXAMPLE"
    val title: String,
    val subtitle: String,
    val targetPayload: String = "",
    val savedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "project_submissions")
data class ProjectSubmissionEntity(
    @PrimaryKey val projectId: String,
    val userHtml: String,
    val isCompleted: Boolean = false,
    val lastEdited: Long = System.currentTimeMillis()
)

@Entity(tableName = "practice_records")
data class PracticeRecordEntity(
    @PrimaryKey val challengeId: String,
    val isCompleted: Boolean = false,
    val attempts: Int = 0,
    val lastAttemptAt: Long = System.currentTimeMillis()
)
