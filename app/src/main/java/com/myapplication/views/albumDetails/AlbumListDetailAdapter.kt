package com.myapplication.views.albumDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.databinding.ItemAlbumDetailListBinding
import com.myapplication.domain.AlbumDetail
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class AlbumListDetailAdapter @Inject constructor() :
    ListAdapter<AlbumDetail, AlbumListDetailAdapter.ViewHolder>(AlbumListDetailDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemAlbumDetailListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AlbumDetail) {
            binding.itemDetail = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAlbumDetailListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class AlbumListDetailDiffCallback : DiffUtil.ItemCallback<AlbumDetail>() {

    override fun areItemsTheSame(oldItem: AlbumDetail, newItem: AlbumDetail): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlbumDetail, newItem: AlbumDetail): Boolean {
        return oldItem == newItem
    }

}
