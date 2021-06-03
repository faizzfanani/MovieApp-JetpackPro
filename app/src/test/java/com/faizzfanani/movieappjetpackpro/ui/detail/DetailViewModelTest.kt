package com.faizzfanani.movieappjetpackpro.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.data.repository.FakeRepository
import com.faizzfanani.movieappjetpackpro.data.source.local.FakeLocalDataSource
import com.faizzfanani.movieappjetpackpro.data.source.remote.FakeRemoteDataSource
import com.faizzfanani.movieappjetpackpro.utils.AppExecutor
import com.faizzfanani.movieappjetpackpro.utils.FakeDataDummy
import com.faizzfanani.movieappjetpackpro.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DetailViewModelTest{
    private val appExecutor = Mockito.mock(AppExecutor::class.java)
    private lateinit var localDataSource : FakeLocalDataSource
    private lateinit var remoteDataSource: FakeRemoteDataSource
    private lateinit var viewModel : DetailViewModel
    private lateinit var fakeMovieDetail : MovieEntity
    private lateinit var fakeTvShowDetail : TvShowEntity
    private lateinit var repository : FakeRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        localDataSource = FakeLocalDataSource()
        remoteDataSource = FakeRemoteDataSource()
        fakeMovieDetail = FakeDataDummy.movieEntity
        fakeTvShowDetail = FakeDataDummy.tvShowEntity
        repository = FakeRepository(localDataSource, remoteDataSource, appExecutor)
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun `get detail movie normal`(){
        //given
        localDataSource.addMovie(fakeMovieDetail)
        //when
        var result : Resource<MovieEntity>? = null
        viewModel.getMovieDetail(578701).observeForever {
            result = it
        }
        //then
        assertNotNull(result?.data)
        assertEquals(fakeMovieDetail, result?.data)
    }

    @Test
    fun `get detail tvShow normal`(){
        //given
        localDataSource.addTvShow(fakeTvShowDetail)
        //when
        var result : Resource<TvShowEntity>? = null
        viewModel.getTvShowDetail(60735).observeForever {
            result = it
        }
        //then
        assertNotNull(result?.data)
        assertEquals(fakeTvShowDetail, result?.data)
    }

}