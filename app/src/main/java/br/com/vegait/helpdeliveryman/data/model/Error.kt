package br.com.vegait.helpdeliveryman.data.model

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("error")
    var errorMessage: String
)