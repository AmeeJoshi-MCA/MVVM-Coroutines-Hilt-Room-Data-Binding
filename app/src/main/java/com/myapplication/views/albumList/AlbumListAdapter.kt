package com.myapplication.views.albumList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.databinding.ItemAlbumListBinding
import com.myapplication.domain.AlbumItem
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class AlbumListAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<AlbumItem, AlbumListAdapter.ViewHolder>(AlbumListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemAlbumListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AlbumItem, clickListener: ClickListener) {
            binding.data = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAlbumListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class AlbumListDiffCallback : DiffUtil.ItemCallback<AlbumItem>() {

    override fun areItemsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean {
        return oldItem == newItem
    }

}

class ClickListener @Inject constructor() {

    var onItemClick: ((AlbumItem) -> Unit)? = null
    var onFavoriteItemClick: ((AlbumItem) -> Unit)? = null

    fun onClick(albumItem: AlbumItem) {
        onItemClick?.invoke(albumItem)
    }

    fun onFavoriteClick(albumItem: AlbumItem) {
        albumItem.isFavorite = albumItem.isFavorite != true
        onFavoriteItemClick?.invoke(albumItem)
    }

}