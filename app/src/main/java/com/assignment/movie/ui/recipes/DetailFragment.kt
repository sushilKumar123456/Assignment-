package com.assignment.movie.ui.recipes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import com.assignment.movie.R
import com.assignment.movie.base.BaseFragment
import com.assignment.movie.utils.ImageUtils
import com.assignment.movie.viewmodel.SearchMovieViewModel
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }
    private val model by viewModel<DetailViewModel>()

    private lateinit var viewModel: DetailViewModel
    @LayoutRes
    override fun getLayoutResId() = R.layout.detail_fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        // TODO: Use the ViewModel
       val id= arguments?.getString("id")

        id?.let {
            viewModel.getMovieDetail(it)?.observe( viewLifecycleOwner, Observer {

                tvTitle.setText(it?.title)
                tvReleased.setText(it?.released)
                tvRuntime.setText(it?.runtime)
                tvGenre.setText(it?.genre)
                tvDirecotr.setText(it?.director)
                tvWriter.setText(it?.writer)
                tvActor.setText(it?.actors)
                tvLanguage.setText(it?.language)
                tvCountry.setText(it?.country)
                tvAward.setText(it?.awards)
                it?.poster?.let { it1 -> ImageUtils.loadImage(it1, ivImage, requireContext()) }
                it?.ratings?.get(1)?.value?.let { it1 -> rated.setRating(it1.split("/")[0].toFloat()) }

                /* tvTitle.setText(it?.title)
                    tvTitle.setText(it?.title)
                    tvTitle.setText(it?.title)
                    tvTitle.setText(it?.title)
                    tvTitle.setText(it?.title)


                    "Language": "Russian",
                    "Country": "Russia",
                    "Awards": "1 nomination.",
                    "Poster"*/
            })
        }
    }

}


