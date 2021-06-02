package com.faizzfanani.movieappjetpackpro.ui.list

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
import org.mockito.Mockito.mock

class ListViewModelTest {

    private val appExecutor = mock(AppExecutor::class.java)
    private lateinit var localDataSource : FakeLocalDataSource
    private lateinit var remoteDataSource: FakeRemoteDataSource
    private lateinit var viewModel : ListViewModel
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
        viewModel = ListViewModel(repository)
    }

    @Test
    fun `get list movie normal`(){
        //given
        localDataSource.addMovies(fakeMovieList)
        //when
        var result : Resource<List<MovieEntity>>? = null
        viewModel.getMovieList().observeForever {
            result = it
        }
        //then
        assertNotNull(result?.data)
        assertEquals(fakeMovieList, result?.data)
    }
    @Test
    fun `get list movie empty`(){
        //given
        remoteDataSource.movieList = emptyList()
        //when
        var result : Resource<List<MovieEntity>>? = null
        viewModel.getMovieList().observeForever {
            it.message?.let { errorMessage ->
                result = Resource.Error(errorMessage, fakeMovieList) }
        }
        //then
        assertEquals(Resource.Error("Data empty", fakeMovieList).message, result?.message)
        assertEquals(Resource.Error("Data empty", fakeMovieList).data, result?.data)
    }
    @Test
    fun `get list tvShow normal`(){
        //given
        localDataSource.addTvShows(fakeTvShowList)
        //when
        var result : Resource<List<TvShowEntity>>? = null
        viewModel.getTvShowList().observeForever {
            result = it
        }
        //then
        assertNotNull(result?.data)
        assertEquals(fakeTvShowList, result?.data)
    }
    @Test
    fun `get list tvShow empty`(){
        //given
        remoteDataSource.tvShowList = emptyList()
        //when
        var result : Resource<List<TvShowEntity>>? = null
        viewModel.getTvShowList().observeForever {
            it.message?.let { errorMessage ->
                result = Resource.Error(errorMessage, fakeTvShowList) }
        }
        //then
        assertEquals(Resource.Error("Data empty", fakeTvShowList).message, result?.message)
        assertEquals(Resource.Error("Data empty", fakeTvShowList).data, result?.data)
    }
}