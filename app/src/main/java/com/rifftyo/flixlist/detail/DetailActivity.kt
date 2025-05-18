package com.rifftyo.flixlist.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rifftyo.flixlist.R
import com.rifftyo.flixlist.core.data.Resource
import com.rifftyo.flixlist.core.domain.model.Movie
import com.rifftyo.flixlist.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding

    private var currentMovie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra("movieId", 0)

        detailViewModel.movieDetail(movieId).observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val movie = resource.data
                    movie?.let {
                        currentMovie = it
                        setStatusFavorite(it.isFavorite)
                        bindMovieDetail(it)
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        binding.btnBookmark.setOnClickListener {
            currentMovie?.let { movie ->
                val newStatus = !movie.isFavorite
                detailViewModel.setFavoriteMovie(movie, newStatus)
                setStatusFavorite(newStatus)
                movie.isFavorite = newStatus
            }
        }

    }

    private fun bindMovieDetail(movie: Movie) {
        binding.apply {
            tvTitle.text = movie.title
            tvReleaseDate.text = movie.releaseDate
            tvGenres.text = movie.genre
            tvOverview.text = movie.overview
            ratingBar.rating = movie.rating.toFloat() / 2

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/original${movie.image}")
                .into(imgPoster)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnBookmark.setImageResource(R.drawable.bookmark_fill_icon)
        } else {
            binding.btnBookmark.setImageResource(R.drawable.bookmark_icon)
        }
    }
}