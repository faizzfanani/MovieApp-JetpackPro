package com.faizzfanani.movieappjetpackpro.ui.home.list.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.databinding.ItemMovieBinding
import com.faizzfanani.movieappjetpackpro.ui.detail.DetailActivity
import com.faizzfanani.movieappjetpackpro.utils.Constant
import com.faizzfanani.movieappjetpackpro.utils.Constant.Companion.id
import com.faizzfanani.movieappjetpackpro.utils.Constant.Companion.tagTvShow
import com.faizzfanani.movieappjetpackpro.utils.Constant.Companion.type

class TvShowAdapter : PagedListAdapter<TvShowEntity, TvShowAdapter.Holder>(DIFF_CALLBACK){
    private val list = arrayListOf<TvShowEntity>()
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
    fun setList(list: List<TvShowEntity>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(holder.itemView.context).load(Constant.imageUrl+list[position].posterPath).override(500,500).into(holder.binding.itemPoster)
        holder.binding.itemTitle.text = list[position].name
        holder.binding.itemOverview.text = list[position].overview
        holder.binding.itemReleaseDate.text = list[position].firstAiringDate
        holder.binding.itemRating.text = list[position].voteAverage.toString()

        //item click listener
        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(id, list[position].id)
            intent.putExtra(type, tagTvShow)
            holder.itemView.context.startActivity(intent)
        }
    }

    class Holder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)
}