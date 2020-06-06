package com.assignment.movie.ui.adapter.utils

import android.view.View
import com.assignment.movie.extension.contains
import com.assignment.movie.extension.gone
import com.assignment.movie.extension.visible
import com.assignment.movie.persitence.MovieDB
import com.assignment.movie.utils.Constants
import com.assignment.movie.utils.ImageUtils
import kotlinx.android.synthetic.main.item_movie.view.*

fun containsLactoseIngredients(ingredients: String?): Boolean {
    return ingredients?.contains(listOf(Constants.CHEESE, Constants.MILK)) ?: false
}

fun setupViews(it: MovieDB, itemView: View) {
    it.thumbnail?.let { it1 -> ImageUtils.loadImage(it1, itemView.recipe_thumb, itemView.context) }
    itemView.recipe_title.text = it.title
    itemView.recipe_ingredients.text = it.ingredients
    checkLactose(it, itemView)
}

private fun checkLactose(it: MovieDB, itemView: View) {
    if (containsLactoseIngredients(it.ingredients)) {
        itemView.recipe_label.visible()
    } else {
        itemView.recipe_label.gone()
    }
}