package com.faizzfanani.movieappjetpackpro.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovies(movies: List<MovieEntity>): List<Long>

    @Query("SELECT * FROM MovieEntity")
    fun getMovieList(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    fun getMovieDetails(id: Int): LiveData<MovieEntity>
}