package com.gart.base.service

import com.gart.base.repository.GartRepository
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.Calls
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

class GithubServiceTest {

    private lateinit var mockRetrofit: MockRetrofit
    private lateinit var retrofit: Retrofit
    private lateinit var delegate: BehaviorDelegate<GithubService>

    @Before
    fun setUp() {

        retrofit = Retrofit.Builder()
            .baseUrl(GithubService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val behavior = NetworkBehavior.create()

        mockRetrofit = MockRetrofit.Builder(retrofit).networkBehavior(behavior).build()
        delegate = mockRetrofit.create(GithubService::class.java)
    }

    @Test
    fun gitHubService_factory_should_create_an_instance_of_the_GithubService() {
        assertTrue(GithubService::class.java.isInstance(GithubService.getInstance()))
    }

    @Test
    fun on_GithubService_searchRepositories_return_an_error_response() {
        val mockGithubService = MockErrorGithubServiceCall(delegate)
        val call = mockGithubService.searchRepositories("", "", "")
        val githubRepositoryResponse = call.execute()

        assertFalse(githubRepositoryResponse.isSuccessful)
    }

    @Test
    fun on_GithubService_searchRepositories_return_a_successful_response() {
        val mockGithubService = MockSuccessGithubServiceCall(delegate)
        val call = mockGithubService.searchRepositories(GartRepository.QUERY, GartRepository.SORT, GartRepository.ORDER)
        val githubRepositoryResponse = call.execute()

        assertTrue(githubRepositoryResponse.isSuccessful)
    }

    class MockErrorGithubServiceCall(private val service: BehaviorDelegate<GithubService>) : GithubService {
        override fun searchRepositories(query: String, sort: String, order: String): Call<JsonObject> {
            val errorResponse = Response.error<JsonObject>(404, ResponseBody.create(MediaType.parse("application/json"), MockErrorResponse.data))
            return service.returning(Calls.response(errorResponse))
                .searchRepositories(query, sort, order)
        }
    }

    class MockSuccessGithubServiceCall(private val service: BehaviorDelegate<GithubService>) : GithubService {
        override fun searchRepositories(query: String, sort: String, order: String): Call<JsonObject> {
            val mockSuccessResponseJsonObject = JsonParser().parse(MockSuccessResponse.data).asJsonObject
            val successResponse = Response.success(mockSuccessResponseJsonObject)
            return service.returningResponse(Response.success(successResponse))
                .searchRepositories(query, sort, order)
        }
    }

}