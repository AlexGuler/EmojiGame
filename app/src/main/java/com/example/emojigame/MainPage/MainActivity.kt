package com.example.emojigame.MainPage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emojigame.APIs.API
import com.example.emojigame.Database.AppDatabase
import com.example.emojigame.Database.Repository
import com.example.emojigame.PuzzlePage.EmojiPuzzleActivity
import com.example.emojigame.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnCategorySelected {

    companion object {
        const val categoryKey = "CataKey"
    }

    private val categoryAdapter: CategoryAdapter = CategoryAdapter()
    private lateinit var viewModel: MainViewModel

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()

        val database = AppDatabase.getInstance(this@MainActivity)
        viewModel = MainViewModelFactory(
            Repository(API, database))
            .create(MainViewModel::class.java)

        loadingProgressbar.show()
        loadCategories()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }

    private fun loadCategories() {
        compositeDisposable.add(
            viewModel.categories.subscribe {
                loadingProgressbar.hide()
                categoryAdapter.submitList(it)
        })
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
