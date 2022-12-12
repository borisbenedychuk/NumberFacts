package com.example.numberfacts.data.remote.model

import com.example.numberfacts.data.cache.model.NumberFactEntity
import com.google.gson.annotations.SerializedName

data class NumberFactResponse(
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("number")
    val number: Int? = null,
) {
    fun asEntity() = NumberFactEntity(
        number = number ?: 0,
        fact = text ?: "",
        timeStamp = System.currentTimeMillis(),
    )
}