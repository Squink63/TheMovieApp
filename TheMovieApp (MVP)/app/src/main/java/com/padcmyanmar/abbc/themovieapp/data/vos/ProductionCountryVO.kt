package com.padcmyanmar.abbc.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class ProductionCountryVO (

    @SerializedName("iso_3166_1")
    val iso31661: String?,

    @SerializedName("name")
    val name: String?
        )