package com.faizzfanani.movieappjetpackpro.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faizzfanani.movieappjetpackpro.data.local.dao.MovieDao
import com.faizzfanani.movieappjetpackpro.data.local.dao.TvShowDao
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity

@androidx.room.Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    companion object {
        private var INSTANCE: Database? = null
        fun getInstance(context: Context): Database {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java, "dbMovie.db"
                )
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE!!
        }
    }
    abstract fun getMovieDao() : MovieDao
    abstract fun getTvShowDao() : TvShowDao
}