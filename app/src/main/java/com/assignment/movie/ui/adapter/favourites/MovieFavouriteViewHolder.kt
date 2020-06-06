package com.assignment.movie.ui.adapter.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.movie.R
import com.assignment.movie.persitence.MovieDB
import com.assignment.movie.ui.adapter.utils.setupViews
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieFavouriteViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_movie, parent, false)) {

    fun bind(
        recipe: MovieDB?,
        listener: FavouritesAdapter.OnItemClickListener
    ) {
        recipe?.let {
            setupViews(it, itemView)
            itemView.recipe_button.text = itemView.context.getString(R.string.Delete)
            setListeners(listener, recipe)
        }
    }

    private fun setListeners(
        listener: FavouritesAdapter.OnItemClickListener,
        recipe: MovieDB
    ) {
        itemView.recipe_button.setOnClickListener {
            listener.onDeleteClick(recipe)
        }
        itemView.recipe_parent.setOnClickListener {
            recipe.href?.let { it1 -> listener.onRecipeRowClicked(it1) }
        }
    }
}