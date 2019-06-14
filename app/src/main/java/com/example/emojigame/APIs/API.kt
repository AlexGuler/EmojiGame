package com.example.emojigame.APIs

import com.example.emojigame.Model.Puzzle
import kotlinx.coroutines.delay

/**
 * API is just a webservice to get our emojis from the server.
 */
object API {
    private const val timeMilli: Long = 750
    private const val PopCategory = "Pop"
    private const val PopDescription = "Name the song"

    // TODO here create a list of puzzles and then maybe aggregate them?

    suspend fun getPuzzlesOfCategory(category: String): List<Puzzle> {

        // TODO do a "group by" category query

        delay(timeMilli)
        return listOf(
            Puzzle(
                PopCategory,
                PopDescription,
                "\uD83D\uDC41️\uD83D\uDC49\uD83D\uDC05",
                "Eye Of The Tiger"
            ),
            // 5
            Puzzle(
                PopCategory,
                PopDescription,
                "\uD83E\uDD81\uD83E\uDD81\uD83E\uDD81\uD83D\uDC55",
                "Three Lions"
            ),
            //
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
            )
        )
    }

    suspend fun getCategories(): List<String> {
        delay(timeMilli)
        return listOf("Pop")
    }
}
