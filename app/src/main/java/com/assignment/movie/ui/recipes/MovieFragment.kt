package com.assignment.movie.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.assignment.movie.R
import com.assignment.movie.api.NetworkState
import com.assignment.movie.base.BaseFragment
import com.assignment.movie.extension.gone
import com.assignment.movie.extension.visible
import com.assignment.movie.persitence.MovieDB
import com.assignment.movie.ui.adapter.movie.RecipeAdapter
import com.assignment.movie.ui.web.WebViewActivity
import com.assignment.movie.viewmodel.FavouritesMovieViewModel
import com.assignment.movie.viewmodel.SearchMovieViewModel
import kotlinx.android.synthetic.main.movie_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MovieFragment : BaseFragment(), RecipeAdapter.OnClickListener {
    @LayoutRes
    override fun getLayoutResId() = R.layout.movie_fragment

    private var query1: String = ""
    private val repositoryRecyclerViewAdapter = RecipeAdapter(this)
    private val model by viewModel<SearchMovieViewModel>()
    private val modelDb by viewModel<FavouritesMovieViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    val apikey="5d81e1ce"
    val  type="movie"
    val plot="full"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
        observeViewModelData()
        observe()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        setupMenu(menu)
    }

    private fun setupMenu(menu: Menu) {
        val searchMenuItem = menu.findItem(R.id.action_search)
        val possibleExistingQuery = model.getCurrentQuery()
        val searchView = searchMenuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        if (possibleExistingQuery.isNotEmpty()) {
            searchMenuItem.expandActionView()
            searchView.setQuery(possibleExistingQuery, false)
            searchView.clearFocus()
        }
        setupSearchViewListener(searchView)
    }

    private fun setupSearchViewListener(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Do nothing in this case
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
               // model.getMovieDetails()
                query1= query!!;
                query?.let {if(it.length>2) model.fetchRecipesByIngredients(it)
                }
                return true
            }

        })
    }

    private fun setupListeners() {
        fragment_button_load_data.setOnClickListener { model.refreshAllList() }
    }

    private fun observeViewModelData() {
        model.networkState?.observe(this, Observer { repositoryRecyclerViewAdapter.updateNetworkState(it)
        if(it==NetworkState.FAILED){

        }})
        model.recipes.observe(this, Observer { repositoryRecyclerViewAdapter.submitList(it)

        })

    }
    private fun observe() {
        modelDb.recipes1.observe(this,
            Observer<PagedList<MovieDB>> {


                        repositoryRecyclerViewAdapter.submitList(it)


            })
    }
    private fun setupRecyclerView() {
        fragment_recipes_recycler_view.adapter = repositoryRecyclerViewAdapter
    }

    //Override onRetryClick from RecipeAdapter.onAddToFavouritesClicked
    override fun onAddToFavouritesClicked(recipe: MovieDB?) {
      /*  recipe?.let {
            model.saveRecipePersistent(recipe)
            toast(recipe.title + " " + getString(R.string.saved_favs))
        }*/
    }

    //Override onRetryClick from RecipeAdapter.onRecipeRowClicked
    override fun onRecipeRowClicked(url: String) {
        activity?.let { WebViewActivity.createIntent(it, url) }?.let { openActivity(it) }
    }

    //Override onRetryClick from RecipeAdapter.OnClickListener
    override fun onRetryClick() {
        model.refreshFailedRequest()
    }

    //Override whenListIsUpdated from RecipeAdapter.OnClickListener
    override fun whenListIsUpdated(size: Int, networkState: NetworkState?) {
        setInitialStates()
        if (size == 0) {
            setSizeZeroInitialState()
            when (networkState) {
                NetworkState.SUCCESS -> {
                    fragment_text_network.text = getString(R.string.recipes_empty)
                    fragment_text_network.visible()
                    fragment_button_load_data.gone()
                }
                NetworkState.FAILED -> {
                    fragment_text_network.text = getString(R.string.error_msg)
                    fragment_image_warning.visible()
                    fragment_text_network.visible()
                    fragment_button_load_data.visible()
                    fragment_animation_view.gone()
                }
                NetworkState.RUNNING -> {
                    fragment_progress_bar.visible()
                }
            }
        } else {
            fragment_animation_view.gone()
        }
    }

    private fun setSizeZeroInitialState() {
        fragment_text_network.text = getString(R.string.recipes_empty)
        fragment_animation_view.visible()
        fragment_text_network.visible()
    }

    private fun setInitialStates() {
        fragment_button_load_data.gone()
        fragment_image_warning.gone()
        fragment_text_network.gone()
        fragment_progress_bar.gone()
    }
}

