package com.example.emojigame.PuzzlePage

import androidx.lifecycle.ViewModel
import com.example.emojigame.Database.Repository
import com.example.emojigame.Model.Puzzle
import io.reactivex.Flowable

class PuzzlePageViewModel(
    category: String,
    private val repository: Repository
) : ViewModel() {

    val puzzles: Flowable<List<Puzzle>> =
        repository.getPuzzles(category)

    override fun onCleared() {
        super.onCleared()
        repository.onClear()
    }
}
