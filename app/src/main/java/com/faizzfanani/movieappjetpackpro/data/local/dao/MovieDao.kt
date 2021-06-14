package com.faizzfanani.movieappjetpackpro.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovies(movies: List<MovieEntity>): List<Long>

    @Query("SELECT * FROM MovieEntity")
    fun getMovieList(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    fun getMovieDetails(id: Int): LiveData<MovieEntity>

    @Query("UPDATE MovieEntity SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavorite(id: Int, isFavorite: Boolean)
    @Query("SELECT isFavorite FROM MovieEntity WHERE id = :id")
    fun isFavorite(id: Int): Boolean
    @Update
    fun update(movieEntity: MovieEntity)

    @Transaction
    fun upsert(movieList: List<MovieEntity>) {
        val insertResult = addMovies(movieList)
        insertResult.forEachIndexed { index, l ->
            if (l == (-1).toLong()) {
                val movies = movieList[index]
                movies.isFavorite = isFavorite(movies.id)
                update(movies)
            }
        }
    }
}