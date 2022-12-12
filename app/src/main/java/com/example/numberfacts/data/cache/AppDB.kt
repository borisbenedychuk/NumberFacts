package com.example.numberfacts.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.numberfacts.data.cache.dao.NumberDao
import com.example.numberfacts.data.cache.model.NumberFactEntity

@Database(entities = [NumberFactEntity::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract val numberDao: NumberDao
}