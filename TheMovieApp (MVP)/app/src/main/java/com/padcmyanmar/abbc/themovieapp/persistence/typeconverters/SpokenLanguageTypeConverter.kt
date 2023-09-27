package com.padcmyanmar.abbc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.abbc.themovieapp.data.vos.SpokenLanguageVO
import org.intellij.lang.annotations.Language

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(spokenLanguages: List<SpokenLanguageVO>?): String {
        return Gson().toJson(spokenLanguages)
    }

    @TypeConverter
    fun toSpokenLanguages(spokenLanguagesJsonString: String): List<SpokenLanguageVO>? {
        val spokenLanguageListType = object : TypeToken<List<SpokenLanguageVO>?>() {}.type
        return Gson().fromJson(spokenLanguagesJsonString, spokenLanguageListType)
    }
}