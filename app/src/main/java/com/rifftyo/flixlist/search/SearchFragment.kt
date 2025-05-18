package com.rifftyo.flixlist.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rifftyo.flixlist.core.data.Resource
import com.rifftyo.flixlist.core.domain.model.Movie
import com.rifftyo.flixlist.core.ui.MovieAdapter
import com.rifftyo.flixlist.databinding.FragmentSearchBinding
import com.rifftyo.flixlist.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = {
                goToDetail(it)
            }

            searchViewModel.allMovies.observe(viewLifecycleOwner) {
                handleMovieState(it, movieAdapter)
            }

            handleRecyclerView(binding.rvMovies, movieAdapter)

            binding.svMovie.addTextChangedListener { editable ->
                searchViewModel.queryChannel.value = editable.toString()
            }

            searchViewModel.searchResultLiveData.observe(viewLifecycleOwner) {
                handleMovieState(it, movieAdapter)
            }
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
        recyclerView.layoutManager = GridLayoutManager(context, 3)
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