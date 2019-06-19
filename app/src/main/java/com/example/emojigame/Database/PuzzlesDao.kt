package com.example.emojigame.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.emojigame.Model.Puzzle
import io.reactivex.Flowable

@Dao
interface PuzzlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPuzzles(puzzles: List<Puzzle>)

    @Query("""
        SELECT * FROM puzzles
        WHERE category = :category
    """)
    fun getPuzzlesByCategory(category: String): Flowable<List<Puzzle>>

}
