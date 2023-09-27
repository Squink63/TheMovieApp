package com.padcmyanmar.abbc.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class ProductionCompanyVO (

    @SerializedName("id")
    val id: Int,

    @SerializedName("logo_path")
    val logoPath: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("original_country")
    val originalCountry: String?
        )