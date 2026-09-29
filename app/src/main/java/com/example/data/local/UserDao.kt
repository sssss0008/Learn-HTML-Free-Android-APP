package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.BookmarkEntity
import com.example.data.model.LessonProgressEntity
import com.example.data.model.NoteEntity
import com.example.data.model.PracticeRecordEntity
import com.example.data.model.ProjectSubmissionEntity
import com.example.data.model.UserProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // User Profile
    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProfile(profile: UserProfileEntity)

    // Lesson Progress
    @Query("SELECT * FROM lesson_progress")
    fun getAllLessonProgress(): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress WHERE lessonId = :lessonId LIMIT 1")
    fun getLessonProgress(lessonId: String): Flow<LessonProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setLessonProgress(progress: LessonProgressEntity)

    @Query("SELECT COUNT(*) FROM lesson_progress WHERE isCompleted = 1")
    fun getCompletedLessonCount(): Flow<Int>

    // Notes
    @Query("SELECT * FROM user_notes ORDER BY updatedAt DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM user_notes WHERE lessonId = :lessonId ORDER BY updatedAt DESC")
    fun getNotesForLesson(lessonId: String): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("DELETE FROM user_notes WHERE id = :noteId")
    suspend fun deleteNote(noteId: Long)

    // Bookmarks
    @Query("SELECT * FROM user_bookmarks ORDER BY savedAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM user_bookmarks WHERE id = :id)")
    fun isBookmarked(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM user_bookmarks WHERE id = :id")
    suspend fun removeBookmark(id: String)

    // Projects
    @Query("SELECT * FROM project_submissions")
    fun getAllProjectSubmissions(): Flow<List<ProjectSubmissionEntity>>

    @Query("SELECT * FROM project_submissions WHERE projectId = :projectId LIMIT 1")
    fun getProjectSubmission(projectId: String): Flow<ProjectSubmissionEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProjectSubmission(project: ProjectSubmissionEntity)

    // Practice Records
    @Query("SELECT * FROM practice_records")
    fun getAllPracticeRecords(): Flow<List<PracticeRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePracticeRecord(record: PracticeRecordEntity)

    // Reset Data
    @Query("DELETE FROM lesson_progress")
    suspend fun clearProgress()

    @Query("DELETE FROM project_submissions")
    suspend fun clearProjects()

    @Query("DELETE FROM practice_records")
    suspend fun clearPractice()
}
