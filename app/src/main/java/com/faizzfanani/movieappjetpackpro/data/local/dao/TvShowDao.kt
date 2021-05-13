package com.faizzfanani.movieappjetpackpro.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity

interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTvShow(tvShowEntity: TvShowEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTvShows(tvShows: List<TvShowEntity>): List<Long>

    @Query("SELECT * FROM TvShowEntity")
    fun getTvShowList(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM TvShowEntity WHERE id = :id")
    fun getTvDetails(id: Int): LiveData<TvShowEntity>
}