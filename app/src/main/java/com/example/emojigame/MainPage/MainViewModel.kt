package com.example.emojigame.MainPage

import androidx.lifecycle.ViewModel
import com.example.emojigame.Database.Repository
import io.reactivex.Flowable

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    val categories: Flowable<List<String>> =
        repository.getCategories()

    override fun onCleared() {
        super.onCleared()
        repository.onClear()
    }
}
