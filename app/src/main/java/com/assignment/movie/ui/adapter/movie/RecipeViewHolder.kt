package com.assignment.movie.ui.adapter.movie

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.assignment.movie.persitence.MovieDB
import com.assignment.movie.ui.adapter.utils.setupViews
import kotlinx.android.synthetic.main.item_movie.view.*

class RecipeViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    fun bind(
        recipe: MovieDB?,
        listener: RecipeAdapter.OnClickListener
    ) {
        recipe?.let {
            setupViews(it, itemView)
            setListeners(listener, recipe)
        }
    }

    private fun setListeners(
        listener: RecipeAdapter.OnClickListener,
        recipe: MovieDB
    ) {
        itemView.recipe_button.setOnClickListener {
            listener.onAddToFavouritesClicked(recipe)
        }
        itemView.recipe_parent.setOnClickListener {
            recipe.href?.let { it1 -> listener.onRecipeRowClicked(it1) }
        }
    }


}
