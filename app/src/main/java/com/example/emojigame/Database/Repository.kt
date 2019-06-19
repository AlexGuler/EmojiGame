package com.example.emojigame.Database

import com.example.emojigame.APIs.API
import com.example.emojigame.Model.Puzzle
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.runBlocking

class Repository(
    private val service: API,
    private val db: AppDatabase
) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getCategories(): Flowable<List<String>> {
        return Flowable.fromCallable {
            runBlocking {
                service.getCategories()
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getPuzzles(category: String): Flowable<List<Puzzle>> {

        compositeDisposable.add(
            Single.fromCallable {
                runBlocking {
                    service.getPuzzlesOfCategory(category)
                }
            }
                .subscribeOn(Schedulers.io())
                .subscribe(db.puzzlesDao()::insertPuzzles, Throwable::printStackTrace)
        )

        return db.puzzlesDao().getPuzzlesByCategory(category)
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun onClear() {
        compositeDisposable.clear()
    }
}
