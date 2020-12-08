package com.nikego.skycapitals.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nikego.skycapitals.data.Score


class Converters {

    @TypeConverter
    fun toScoreList(value: String?): List<Score>? {
        val type = object : TypeToken<List<Score>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromScoreList(scores: List<Score>?): String? =
        Gson().toJson(scores)
}