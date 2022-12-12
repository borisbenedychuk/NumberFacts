package com.example.numberfacts.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.numberfacts.domain.model.NumberFactModel

@Entity(tableName = "numbers", primaryKeys = ["number", "fact"])
data class NumberFactEntity(
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "fact")
    val fact: String,
    @ColumnInfo(name = "time_stamp")
    val timeStamp: Long,
) {
    fun asModel(): NumberFactModel = NumberFactModel(
        number = number,
        fact = fact,
    )
}