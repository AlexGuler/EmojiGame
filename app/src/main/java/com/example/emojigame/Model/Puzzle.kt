package com.example.emojigame.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puzzles")
data class Puzzle(
    @ColumnInfo(name = "category")
    val category: String,
    val description: String,
    @PrimaryKey
    val emojiPuzzle: String,
    val answer: String
)
