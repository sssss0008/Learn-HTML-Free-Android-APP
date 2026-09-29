package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.BookmarkEntity
import com.example.data.model.LessonProgressEntity
import com.example.data.model.NoteEntity
import com.example.data.model.PracticeRecordEntity
import com.example.data.model.ProjectSubmissionEntity
import com.example.data.model.UserProfileEntity

@Database(
    entities = [
        UserProfileEntity::class,
        LessonProgressEntity::class,
        NoteEntity::class,
        BookmarkEntity::class,
        ProjectSubmissionEntity::class,
        PracticeRecordEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "learn_html_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
