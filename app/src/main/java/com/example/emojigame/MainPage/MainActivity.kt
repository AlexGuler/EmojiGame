package com.example.emojigame.MainPage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emojigame.APIs.API
import com.example.emojigame.PuzzlePage.EmojiPuzzleActivity
import com.example.emojigame.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnCategorySelected {

    companion object {
        const val categoryKey = "CataKey"
    }

    private val categoryAdapter: CategoryAdapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()

        loadingProgressbar.show()
        loadCategories()
    }

    private fun loadCategories() {
        GlobalScope.launch(Dispatchers.Main) {
            loadingProgressbar.hide()
            val categories = API.getCategories()
            categoryAdapter.submitList(categories)
        }
    }

    private fun setupRecyclerView() {
        categoryAdapter.onCategorySelected = this
        categoriesRecyclerView.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onCategorySelected(category: String) {
        val intent = Intent(this@MainActivity, EmojiPuzzleActivity::class.java)
        intent.putExtra(categoryKey, category)
        startActivity(intent)
    }
}
