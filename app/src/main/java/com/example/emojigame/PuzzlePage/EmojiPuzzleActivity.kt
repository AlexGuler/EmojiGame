package com.example.emojigame.PuzzlePage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.emojigame.APIs.API
import com.example.emojigame.Database.AppDatabase
import com.example.emojigame.Database.Repository
import com.example.emojigame.MainPage.MainActivity
import com.example.emojigame.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_emoji_puzzle.*

class EmojiPuzzleActivity : AppCompatActivity() {

    private val emojiPuzzleAdapter: EmojiPuzzleAdapter = EmojiPuzzleAdapter()

    private val compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: PuzzlePageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emoji_puzzle)
        val category = intent.getStringExtra(MainActivity.categoryKey)
        categoryTitle.text = category

        val database = AppDatabase.getInstance(this@EmojiPuzzleActivity)
        viewModel = PuzzlePageViewModelFactory(
            category!!,
            Repository(API, database))
            .create(PuzzlePageViewModel::class.java)

        setupPuzzlesRecyclerView()
        loadingProgressbar.show()
        loadPuzzles()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }

    private fun loadPuzzles() {
        compositeDisposable.add(
            viewModel.puzzles.subscribe {
                loadingProgressbar.hide()
                emojiPuzzleAdapter.submitList(it)
            })
    }

    private fun setupPuzzlesRecyclerView() {
        puzzlesRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@EmojiPuzzleActivity, RecyclerView.HORIZONTAL, false)
            adapter = emojiPuzzleAdapter
        }
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(puzzlesRecyclerview)
    }
}
