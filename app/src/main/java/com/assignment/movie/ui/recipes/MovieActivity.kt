package com.assignment.movie.ui.recipes

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.assignment.movie.R
import com.assignment.movie.base.BaseActivity
import com.assignment.movie.base.BaseFragment
import com.assignment.movie.ui.favourites.FavouritesMovieActivity
import kotlinx.android.synthetic.main.activity_main_movie.*

class MovieActivity : BaseActivity(false) {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main_movie

    override fun fragment(): BaseFragment = MovieFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupClickButton()
    }

    private fun setupClickButton() {
        fab_favourites.setOnClickListener {
            this.startActivity(
                FavouritesMovieActivity.createIntent(this)
            )
        }
    }
}
