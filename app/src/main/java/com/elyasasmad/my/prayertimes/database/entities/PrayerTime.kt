package com.elyasasmad.my.prayertimes.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class PrayerTimeDB (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val imsak: String,
    val subuh: String,
    val syuruk: String,
    val zohor: String,
    val asar: String,
    val maghrib: String,
    val isyak: String,
    val timestamp: Timestamp
)