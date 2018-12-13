package com.shurik.homeaccountant.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database (entities = [Counts::class],version = 15,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countsDao() : CountsDao

    companion object {
        private const val databaseName = "countsDatabase"

        var dbInstance : AppDatabase? = null
        fun getInstance (context: Context?) : AppDatabase? {
            if (dbInstance == null)
                dbInstance = Room.databaseBuilder(context!!.applicationContext,
                        AppDatabase::class.java, databaseName).allowMainThreadQueries()
                        .fallbackToDestructiveMigration().build()
            return dbInstance
        }
    }
}