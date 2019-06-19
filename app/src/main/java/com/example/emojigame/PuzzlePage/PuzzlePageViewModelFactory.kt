package com.example.emojigame.PuzzlePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.emojigame.Database.Repository

class PuzzlePageViewModelFactory(
    private val category: String,
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PuzzlePageViewModel(category, repository) as T
    }
}