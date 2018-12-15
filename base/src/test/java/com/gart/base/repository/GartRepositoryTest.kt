package com.gart.base.repository

import com.gart.base.database.RepositoryDao
import com.gart.base.model.GithubRepository
import com.gart.base.service.GithubService
import com.gart.base.service.MockSuccessResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import retrofit2.Call
import retrofit2.Response


class GartRepositoryTest {

    private lateinit var repository: GartRepository

    @Mock
    private lateinit var mockGithubService: GithubService
    @Mock
    private lateinit var mockRepositoryDao: RepositoryDao
    @Mock
    private lateinit var mockCall: Call<JsonObject>

    @Before
    fun setUp() {
        initMocks(this)

        repository = GartRepository(mockGithubService, mockRepositoryDao)
    }

    @Test
    fun make_a_service_call_when_repository_fetchRemoteRepositories_is_invoked() {
        val mockSuccessResponseJsonObject = JsonParser().parse(MockSuccessResponse.data).asJsonObject
        val successResponse = Response.success(mockSuccessResponseJsonObject)

        `when`(mockGithubService.searchRepositories(GartRepository.QUERY, GartRepository.SORT, GartRepository.ORDER)).thenReturn(mockCall)
        `when`(mockCall.execute()).thenReturn(successResponse)

        repository.fetchGithubRepositories()

        assertTrue(successResponse.isSuccessful)
    }

    @Test
    fun getAllRepositories_from_the_database_when_repository_getGithubRepositories_is_invoked() {
        repository.getGithubRepositories()

        verify(mockRepositoryDao).getAllRepositories()
    }

    @Test
    fun getRepository_from_the_database_when_repository_getGithubRepository_is_invoked() {
        val repositoryId = 1

        repository.getGithubRepository(repositoryId)

        verify(mockRepositoryDao).getRepository(repositoryId)
    }

    @Test
    fun insertAll_to_the_database_when_repository_saveGithubRepositories_is_invoked() {
        val githubRepositoryItemList = arrayListOf<GithubRepository>()

        repository.saveGithubRepositories(githubRepositoryItemList)

        verify(mockRepositoryDao).insertAll(githubRepositoryItemList)
    }

}
