package com.example.numberfacts.presentation.model.details

import android.os.Parcelable
import com.example.numberfacts.domain.model.NumberFactModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class NumberFactDetailsItem(
    val number: Int,
    val fact: String,
) : Parcelable

fun NumberFactModel.asItem() = NumberFactDetailsItem(
    number = number,
    fact = fact,
)