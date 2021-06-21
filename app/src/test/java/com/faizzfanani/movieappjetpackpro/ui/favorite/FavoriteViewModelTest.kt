package com.faizzfanani.movieappjetpackpro.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.data.repository.FakeRepository
import com.faizzfanani.movieappjetpackpro.data.source.local.FakeLocalDataSource
import com.faizzfanani.movieappjetpackpro.data.source.remote.FakeRemoteDataSource
import com.faizzfanani.movieappjetpackpro.ui.favorite.list.FavoriteViewModel
import com.faizzfanani.movieappjetpackpro.utils.AppExecutor
import com.faizzfanani.movieappjetpackpro.utils.FakeDataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class FavoriteViewModelTest{
    private val appExecutor = Mockito.mock(AppExecutor::class.java)
    private lateinit var localDataSource : FakeLocalDataSource
    private lateinit var remoteDataSource: FakeRemoteDataSource
    private lateinit var viewModel : FavoriteViewModel
    private lateinit var fakeMovieList : List<MovieEntity>
    private lateinit var fakeTvShowList : List<TvShowEntity>
    private lateinit var repository : FakeRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        localDataSource = FakeLocalDataSource()
        remoteDataSource = FakeRemoteDataSource()
        fakeMovieList = FakeDataDummy.movieList
        fakeTvShowList = FakeDataDummy.tvShowList
        repository = FakeRepository(localDataSource, remoteDataSource, appExecutor)
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun `get list movie favorite`(){
        //given
        localDataSource.addMovies(fakeMovieList)
        //when
        var result : PagedList<MovieEntity>? = null
        viewModel.getMovieFavorite().observeForever {
            result = it
        }
        //then
        assertNotNull(result)
        assertEquals(fakeMovieList, result)
    }

    @Test
    fun `get list tvShow favorite`(){
        //given
        localDataSource.addTvShows(fakeTvShowList)
        //when
        var result : PagedList<TvShowEntity>? = null
        viewModel.getTvShowFavorite().observeForever {
            result = it
        }
        //then
        assertNotNull(result)
        assertEquals(fakeTvShowList, result)
    }
}