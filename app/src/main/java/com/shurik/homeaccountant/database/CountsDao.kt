package com.shurik.homeaccountant.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface CountsDao {

    companion object {
        const val DATE = "date"
    }

    @Query("SELECT * from counts ORDER BY id DESC")
    fun getCounts() : List<Counts>

    @Query("SELECT  * FROM counts ORDER BY id DESC LIMIT 1")
    fun getLastData() : List<Counts>

    @Query("SELECT * FROM counts WHERE $DATE=:date")
    fun getMonthDetail(date : String) : List<Counts>

    @Insert(onConflict = REPLACE)
    fun addData (counts: Counts)

    @Update
    fun updateCounts (counts: Counts)

}