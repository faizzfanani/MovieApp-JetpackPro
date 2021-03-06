package com.faizzfanani.movieappjetpackpro.ui.list.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.databinding.ItemMovieBinding
import com.faizzfanani.movieappjetpackpro.ui.detail.DetailActivity
import com.faizzfanani.movieappjetpackpro.utils.Constant

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.Holder>(){
    private val list = arrayListOf<TvShowEntity>()
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
            intent.putExtra("id", list[position].id)
            intent.putExtra("type", "tvShow")
            holder.itemView.context.startActivity(intent)
        }
    }

    class Holder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)
}