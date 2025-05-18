package com.rifftyo.flixlist.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rifftyo.flixlist.core.data.Resource
import com.rifftyo.flixlist.core.domain.model.Movie
import com.rifftyo.flixlist.core.ui.MovieAdapter
import com.rifftyo.flixlist.databinding.FragmentHomeBinding
import com.rifftyo.flixlist.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            // Now Playing Movies
            val nowPlayingAdapter = MovieAdapter()
            nowPlayingAdapter.onItemClick = {
                goToDetail(it)
            }

            homeViewModel.nowPlayingMovies.observe(viewLifecycleOwner) {
                handleMovieState(it, nowPlayingAdapter)
            }

            handleRecyclerView(binding.rvNowPlayingList, nowPlayingAdapter)

            // Popular Movies
            val popularAdapter = MovieAdapter()
            popularAdapter.onItemClick = {
                goToDetail(it)
            }

            homeViewModel.popularMovies.observe(viewLifecycleOwner) {
                handleMovieState(it, popularAdapter)
            }

            handleRecyclerView(binding.rvPopularList, popularAdapter)

            // Top Rated Movies
            val topRatedAdapter = MovieAdapter()
            topRatedAdapter.onItemClick = {
                goToDetail(it)
            }

            homeViewModel.topRatedMovies.observe(viewLifecycleOwner) {
                handleMovieState(it, topRatedAdapter)
            }

            handleRecyclerView(binding.rvTopRatedList, topRatedAdapter)

            // Upcoming Movies
            val upcomingAdapter = MovieAdapter()
            upcomingAdapter.onItemClick = {
                goToDetail(it)
            }

            homeViewModel.upcomingMovies.observe(viewLifecycleOwner) {
                handleMovieState(it, upcomingAdapter)
            }

            handleRecyclerView(binding.rvUpcomingList, upcomingAdapter)
        }
    }

    private fun handleMovieState(result: Resource<List<Movie>>, adapter: MovieAdapter) {
        when (result) {
            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
            is Resource.Success -> {
                binding.progressBar.visibility = View.GONE
                adapter.submitList(result.data)
            }
            is Resource.Error -> {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun handleRecyclerView(recyclerView: RecyclerView, adapter: MovieAdapter) {
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun goToDetail(movie: Movie) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("movieId", movie.movieId)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}