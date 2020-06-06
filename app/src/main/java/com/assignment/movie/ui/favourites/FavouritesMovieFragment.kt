package com.assignment.movie.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import com.assignment.movie.R
import com.assignment.movie.base.BaseFragment
import com.assignment.movie.extension.gone
import com.assignment.movie.extension.visible
import com.assignment.movie.persitence.MovieDB
import com.assignment.movie.ui.adapter.favourites.FavouritesAdapter
import com.assignment.movie.ui.web.WebViewActivity
import com.assignment.movie.viewmodel.FavouritesMovieViewModel
import kotlinx.android.synthetic.main.favourites_fragment.*
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesMovieFragment : BaseFragment(), FavouritesAdapter.OnItemClickListener {

    private val recipeFavouritesAdapter = FavouritesAdapter(arrayListOf(), this)
    private val model by viewModel<FavouritesMovieViewModel>()

    @LayoutRes
    override fun getLayoutResId() = R.layout.favourites_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observe()
        model.loadRecipesPersistence()
    }

    private fun setupRecyclerView() {
        fragment_favourites_recycler_view.adapter = recipeFavouritesAdapter
    }

    private fun observe() {
        model.recipes.observe(this,
            Observer<List<MovieDB>> {
                it?.let {
                    if (it.isNotEmpty()) {
                        showNormalState()
                        recipeFavouritesAdapter.replaceData(it)
                    } else {
                        showEmptyState()
                    }
                }
            })
    }

    private fun showEmptyState() {
        fragment_favourites_recycler_view.gone()
        favourites_view_empty.visible()
    }

    private fun showNormalState() {
        fragment_favourites_recycler_view.visible()
        favourites_view_empty.gone()
    }

    override fun onDeleteClick(recipeDB: MovieDB) {

    }

    override fun onRecipeRowClicked(href: String) {
        activity?.let { WebViewActivity.createIntent(it, href) }?.let { openActivity(it) }
    }
}