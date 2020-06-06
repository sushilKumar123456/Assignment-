package com.assignment.movie.ui.adapter.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.movie.persitence.MovieDB

class FavouritesAdapter(
    private var items: List<MovieDB>?,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieFavouriteViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as MovieFavouriteViewHolder).bind(items?.get(position), listener)

    override fun getItemCount(): Int {
        return if (items != null) {
            items!!.size
        } else 0
    }

    fun replaceData(items: List<MovieDB>?) {
        this.items = items
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onDeleteClick(recipeDB: MovieDB)
        fun onRecipeRowClicked(href: String)
    }

}