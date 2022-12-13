package com.example.numberfacts.data.cache.dao

import androidx.room.*
import com.example.numberfacts.data.cache.model.NumberFactEntity
import io.reactivex.rxjava3.core.Observable

private const val HISTORY_LIMIT = 10

@Dao
interface NumberDao {

    @Query("SELECT * FROM numbers ORDER BY time_stamp DESC")
    fun getEntities(): Observable<List<NumberFactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntity(entity: NumberFactEntity)

    @Query("DELETE FROM numbers WHERE time_stamp NOT IN (SELECT time_stamp FROM numbers ORDER BY time_stamp DESC LIMIT $HISTORY_LIMIT)")
    fun trimHistory()

    @Transaction
    fun insertEntityAndTrimHistory(entity: NumberFactEntity) {
        insertEntity(entity)
        trimHistory()
    }
}