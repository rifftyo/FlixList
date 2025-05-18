package com.rifftyo.flixlist.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rifftyo.flixlist.BookmarkModuleDependencies
import com.rifftyo.flixlist.bookmark.databinding.ActivityBookmarkBinding
import com.rifftyo.flixlist.core.domain.model.Movie
import com.rifftyo.flixlist.core.ui.MovieAdapter
import com.rifftyo.flixlist.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class BookmarkActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val bookmarkViewModel: BookmarkViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityBookmarkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerBookmarkComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    BookmarkModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookmarkAdapter = MovieAdapter()

        handleRecyclerView(binding.rvFavorit, bookmarkAdapter)

        bookmarkAdapter.onItemClick = {
            goToDetail(it)
        }

        bookmarkViewModel.getFavoriteMovie.observe(this) { movieList ->
            bookmarkAdapter.submitList(movieList)

            binding.tvEmpty.visibility = if (movieList.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun handleRecyclerView(recyclerView: RecyclerView, adapter: MovieAdapter) {
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun goToDetail(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movieId", movie.movieId)
        startActivity(intent)
    }
}