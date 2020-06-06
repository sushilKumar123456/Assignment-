package com.assignment.movie.ui.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.movie.R
import com.assignment.movie.api.NetworkState
import com.assignment.movie.persitence.MovieDB


class RecipeAdapter(private val listener: OnClickListener) :
    PagedListAdapter<MovieDB, RecyclerView.ViewHolder>(diffCallback) {

    private var currentNetworkState: NetworkState? = null

    interface OnClickListener {
        fun onRetryClick()
        fun whenListIsUpdated(size: Int, networkState: NetworkState?)
        fun onAddToFavouritesClicked(recipe: MovieDB?)
        fun onRecipeRowClicked(url: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_movie -> RecipeViewHolder(view)
            R.layout.item_repo_state -> RepoStateViewHolder(view)
            else -> throw IllegalArgumentException(parent.context.getString(R.string.viewtype_creation_error))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_movie -> (holder as RecipeViewHolder).bind(getItem(position), listener)
            R.layout.item_repo_state -> (holder as RepoStateViewHolder).bind(currentNetworkState, listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_repo_state
        } else {
            R.layout.item_movie
        }
    }

    override fun getItemCount(): Int {
        this.listener.whenListIsUpdated(super.getItemCount(), this.currentNetworkState)
        return super.getItemCount()
    }

    private fun hasExtraRow() = currentNetworkState != null && currentNetworkState != NetworkState.SUCCESS

    fun updateNetworkState(newNetworkState: NetworkState?) {
        val currentNetworkState = this.currentNetworkState
        val hadExtraRow = hasExtraRow()
        this.currentNetworkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        determineItemChange(hadExtraRow, hasExtraRow, currentNetworkState, newNetworkState)
    }

    private fun determineItemChange(
        hadExtraRow: Boolean, hasExtraRow: Boolean,
        currentNetworkState: NetworkState?,
        newNetworkState: NetworkState?
    ) {
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && currentNetworkState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MovieDB>() {
            override fun areItemsTheSame(oldItem: MovieDB, newItem: MovieDB): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: MovieDB, newItem: MovieDB): Boolean = oldItem == newItem
        }
    }
}
