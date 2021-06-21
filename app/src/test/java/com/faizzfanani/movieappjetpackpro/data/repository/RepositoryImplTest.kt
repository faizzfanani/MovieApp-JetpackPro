package com.faizzfanani.movieappjetpackpro.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.faizzfanani.movieappjetpackpro.data.Repository
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.data.source.local.FakeLocalDataSource
import com.faizzfanani.movieappjetpackpro.data.source.remote.FakeRemoteDataSource
import com.faizzfanani.movieappjetpackpro.utils.AppExecutor
import com.faizzfanani.movieappjetpackpro.utils.FakeDataDummy
import com.faizzfanani.movieappjetpackpro.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class RepositoryImplTest{

    private val appExecutor = mock(AppExecutor::class.java)
    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var remoteDataSource: FakeRemoteDataSource
    private lateinit var repository: Repository
    private lateinit var fakeMovieList : List<MovieEntity>
    private lateinit var fakeTvShowList : List<TvShowEntity>
    private lateinit var fakeMovieDetail : MovieEntity
    private lateinit var fakeTvShowDetail : TvShowEntity

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init(){
        fakeMovieList = FakeDataDummy.movieList
        fakeTvShowList = FakeDataDummy.tvShowList
        fakeMovieDetail = FakeDataDummy.movieEntity
        fakeTvShowDetail = FakeDataDummy.tvShowEntity
        localDataSource = FakeLocalDataSource()
        remoteDataSource = FakeRemoteDataSource()
        repository = FakeRepository(localDataSource, remoteDataSource, appExecutor)
    }

    //List
    @Test
    fun `get list movie`(){
        //given
        localDataSource.addMovies(fakeMovieList)
        //when
        var result : Resource<PagedList<MovieEntity>>? = null
        repository.getMovieList().observeForever {
            result = it
        }
        //then
        assertNotNull(result?.data)
        assertEquals(fakeMovieList, result?.data)
    }
    @Test
    fun `get list tvShow`(){
        //given
        localDataSource.addTvShows(fakeTvShowList)
        //when
        var result : Resource<PagedList<TvShowEntity>>? = null
        repository.getTvShowList().observeForever {
            result = it
        }
        //then
        assertNotNull(result?.data)
        assertEquals(fakeTvShowList, result?.data)
    }

    //Favorite
    @Test
    fun `get list movie favorite`(){
        //given
        localDataSource.addMovies(fakeMovieList)
        //when
        var result : PagedList<MovieEntity>? = null
        repository.getMovieFavorite().observeForever {
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
        repository.getTvShowFavorite().observeForever {
            result = it
        }
        //then
        assertNotNull(result)
        assertEquals(fakeTvShowList, result)
    }

    //Detail
    @Test
    fun `get detail movie`(){
        //given
        localDataSource.addMovie(fakeMovieDetail)
        //when
        var result : Resource<MovieEntity>? = null
        repository.getMovieDetails(578701).observeForever {
            result = it
        }
        //then
        assertNotNull(result?.data)
        assertEquals(fakeMovieDetail, result?.data)
    }
    @Test
    fun `get detail tvShow`(){
        //given
        localDataSource.addTvShow(fakeTvShowDetail)
        //when
        var result : Resource<TvShowEntity>? = null
        repository.getTvShowDetails(60735).observeForever {
            result = it
        }
        //then
        assertNotNull(result?.data)
        assertEquals(fakeTvShowDetail, result?.data)
    }
}