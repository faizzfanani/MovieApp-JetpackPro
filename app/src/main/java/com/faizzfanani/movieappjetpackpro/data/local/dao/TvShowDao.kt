package com.faizzfanani.movieappjetpackpro.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTvShow(tvShowEntity: TvShowEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTvShows(tvShows: List<TvShowEntity>): List<Long>

    @Query("SELECT * FROM TvShowEntity")
    fun getTvShowList(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM TvShowEntity WHERE isFavorite = 1")
    fun getTvShowFavorite(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM TvShowEntity WHERE id = :id")
    fun getTvDetails(id: Int): LiveData<TvShowEntity>

    @Query("UPDATE TvShowEntity SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavorite(id: Int, isFavorite: Boolean)

    @Query("SELECT isFavorite FROM TvShowEntity WHERE id = :id")
    fun isFavorite(id: Int): Boolean

    @Update
    fun update(tvShowEntity: TvShowEntity)

    @Transaction
    fun upsert(tvShowList: List<TvShowEntity>) {
        val insertResult = addTvShows(tvShowList)
        insertResult.forEachIndexed { index, l ->
            if (l == (-1).toLong()) {
                val tvShows = tvShowList[index]
                tvShows.isFavorite = isFavorite(tvShows.id)
                update(tvShows)
            }
        }
    }
}