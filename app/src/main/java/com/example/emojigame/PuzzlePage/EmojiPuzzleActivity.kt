package com.example.emojigame.PuzzlePage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.example.emojigame.APIs.API
import com.example.emojigame.MainPage.MainActivity
import com.example.emojigame.R
import kotlinx.android.synthetic.main.activity_emoji_puzzle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EmojiPuzzleActivity : AppCompatActivity() {

    private val emojiPuzzleAdapter: EmojiPuzzleAdapter = EmojiPuzzleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emoji_puzzle)
        val category = intent.getStringExtra(MainActivity.categoryKey)
        categoryTitle.text = category

        setupPuzzlesRecyclerView()
        loadingProgressbar.show()
        loadPuzzles(category)
    }

    private fun loadPuzzles(category: String?) {
        if (category != null) {
            GlobalScope.launch(Dispatchers.Main) {
                loadingProgressbar.hide()
                val puzzles = API.getPuzzlesOfCategory(category)
                emojiPuzzleAdapter.submitList(puzzles)
            }
        } else {
            loadingProgressbar.hide()
            Toast.makeText(
                this@EmojiPuzzleActivity,
                "Error occurred could not retrieve puzzles, please try again later.",
                Toast.LENGTH_SHORT
            ).show()
        }
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
