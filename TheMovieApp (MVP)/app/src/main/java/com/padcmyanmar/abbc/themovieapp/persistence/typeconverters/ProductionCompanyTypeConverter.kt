package com.padcmyanmar.abbc.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.abbc.themovieapp.data.vos.ProductionCompanyVO

class ProductionCompanyTypeConverter {
    @TypeConverter
    fun toString(productionCompanies: List<ProductionCompanyVO>?): String {
        return Gson().toJson(productionCompanies)
    }

    @TypeConverter
    fun toProductionCompanies(productionCompaniesJSonString: String): List<ProductionCompanyVO>? {
        val productionCompaniesListType = object : TypeToken<List<ProductionCompanyVO>?>() {}.type
        return Gson().fromJson(productionCompaniesJSonString, productionCompaniesListType)
    }
}