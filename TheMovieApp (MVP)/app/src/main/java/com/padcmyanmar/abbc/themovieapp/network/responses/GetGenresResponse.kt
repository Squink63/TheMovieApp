package com.padcmyanmar.abbc.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.abbc.themovieapp.data.vos.GenreVO

data class GetGenresResponse (

    @SerializedName("genres")
    val genres : List<GenreVO>
        )