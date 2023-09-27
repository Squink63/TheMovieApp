package com.padcmyanmar.abbc.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.abbc.themovieapp.data.vos.ActorVO

data class GetCreditsByMovieResponse (

    @SerializedName("cast")
    val cast : List<ActorVO>?,

    @SerializedName("crew")
    val crew: List<ActorVO>?
        )