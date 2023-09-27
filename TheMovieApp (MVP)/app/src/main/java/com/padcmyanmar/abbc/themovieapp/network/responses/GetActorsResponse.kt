package com.padcmyanmar.abbc.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.abbc.themovieapp.data.vos.ActorVO

data class GetActorsResponse (

    @SerializedName("results")
    val results : List<ActorVO>?
        )