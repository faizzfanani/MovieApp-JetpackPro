package com.faizzfanani.movieappjetpackpro.ui.list.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.databinding.ItemMovieBinding
import com.faizzfanani.movieappjetpackpro.ui.detail.DetailActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.Holder>(){
    private val list = arrayListOf<MovieEntity>()
    fun setList(list: List<MovieEntity>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        Glide.with(holder.itemView.context).load(list[position].posterPath).override(500,500).into(holder.binding.itemPoster)
        holder.binding.itemTitle.text = list[position].title
        holder.binding.itemOverview.text = list[position].overview
        holder.binding.itemReleaseDate.text = list[position].releaseDate
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