package com.example.android2021.kt04

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    val `user`: User
)