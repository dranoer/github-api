package com.dranoer.abnamro.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dranoer.abnamro.data.model.RepoEntity

@Database(entities = [RepoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}