package com.elyasasmad.my.prayertimes.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PrayerTimeResponse(
    val prayerTime: List<PrayerTime>,
    val bearing: String,
    val lang: String,
    val periodType: String,
    val serverTime: String,
    val status: String,
    val zone: String
)

@Serializable
data class PrayerTime(
    val hijri: String,
    val date: String,
    val day: String,
    val imsak: String,
    val fajr: String,
    val syuruk: String,
    val dhuhr: String,
    val asr: String,
    val maghrib: String,
    val isha: String
)