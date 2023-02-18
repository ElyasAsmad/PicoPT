package com.elyasasmad.my.prayertimes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.elyasasmad.my.prayertimes.data.remote.dto.PrayerTime

@Dao
interface PrayerTimeDAO {
    @Insert
    fun insert(prayerTime: PrayerTime)

    @Delete
    fun delete(index: Int)

//    @Query("SELECT * FROM Prayer")
}