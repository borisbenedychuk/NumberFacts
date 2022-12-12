package com.example.numberfacts.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.numberfacts.data.cache.model.NumberFactEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface NumberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntity(entity: NumberFactEntity): Completable

    @Query("SELECT * FROM numbers ORDER BY time_stamp DESC")
    fun getEntities(): Observable<List<NumberFactEntity>>
}