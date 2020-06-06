package com.assignment.movie.ui.favourites

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.assignment.movie.R
import com.assignment.movie.base.BaseActivity
import com.assignment.movie.base.BaseFragment

class FavouritesMovieActivity : BaseActivity(true) {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main

    override fun fragment(): BaseFragment = FavouritesMovieFragment()

    companion object {

        fun createIntent(activity: Activity): Intent {

            return Intent(activity, FavouritesMovieActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.favourite_activity_title)
    }
}