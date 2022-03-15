package com.myapplication.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class AlbumDatabaseTest  {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    //This rule tells android test to run everything one by one ,
    // without this u will get a error saying cannot complete task

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AlbumDatabase

    private lateinit var dao: AlbumDao

    @Before
    fun setUp() {
        hiltRule.inject()
        dao = database.albumDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testGetAlbum() {
        val databaseAlbumListItem = DatabaseAlbumListItem(
            id =  1,
            userId = 1,
            title = "quidem molestiae enim test",
            isFavorite = false
        )

        dao.insertAlbum(databaseAlbumListItem)
        val allUsers = dao.getDatabaseAlbum().getOrAwaitValue()
        assertThat(allUsers).contains(databaseAlbumListItem)
    }


    @Test
    fun testGetAlbumDetail() {

        val albumDetailList : List<DatabaseAlbumDetails> = listOf(
            DatabaseAlbumDetails (
                id =  1,
                albumId = 2,
                thumbnailUrl = "https://via.placeholder.com/150/92c952",
                title = "accusamus beatae ad facilis cum similique qui sunt",
                url="https://via.placeholder.com/600/92c952"
               )
        )

        dao.insertAlbumDetail(albumDetailList)
        val allAlbumDetail = dao.getAlbumDetails(2).getOrAwaitValue()
        Assert.assertEquals("The id's did match", 1, allAlbumDetail[0].id)

    }
}