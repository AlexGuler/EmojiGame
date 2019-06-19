package com.example.emojigame.APIs

import com.example.emojigame.Model.Puzzle
import kotlinx.coroutines.delay

/**
 * API is just a webservice to get our emojis from the server.
 */
object API {

    private const val timeMilli: Long = 1000
    private const val PopCategory = "Pop"
    private const val PopDescription = "Name the song"

    private val puzzles: List<Puzzle> =
        listOf(
            Puzzle(
                PopCategory,
                PopDescription,
                "\uD83D\uDC41️\uD83D\uDC49\uD83D\uDC05",
                "Eye Of The Tiger"
            ),
            Puzzle(
                PopCategory,
                PopDescription,
                "\uD83E\uDD81\uD83E\uDD81\uD83E\uDD81\uD83D\uDC55",
                "Three Lions"
            ),
            Puzzle(
                PopCategory,
                PopDescription,
                "\uD83C\uDF0D\uD83C\uDF0E\uD83C\uDF0F\uD83C\uDFBC",
                "Earth Song"
            ),
            Puzzle(
                PopCategory,
                PopDescription,
                "\uD83D\uDC4B\uD83D\uDC4B\uD83D\uDC4B",
                "Bye Bye Bye"
            ))

    suspend fun getPuzzlesOfCategory(category: String): List<Puzzle> {
        delay(timeMilli)
        return puzzles.filter { it.category == category }
    }

    suspend fun getCategories(): List<String> {
        delay(timeMilli)
        return listOf("Pop")
    }
}
