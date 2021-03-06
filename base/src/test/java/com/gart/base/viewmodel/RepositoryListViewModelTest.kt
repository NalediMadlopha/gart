package com.gart.base.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.gart.base.repository.GartRepository
import com.gart.base.service.MockErrorResponse
import com.gart.base.service.MockSuccessResponse
import com.gart.base.utils.Utils
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.Spy
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Response

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE, sdk = [21])
class RepositoryListViewModelTest {

    private lateinit var viewModel: RepositoryListViewModel

    private lateinit var successResponse: Response<JsonObject>
    private lateinit var errorResponse: Response<JsonObject>

    @Mock
    private lateinit var mockApplication: Application
    @Mock
    private lateinit var mockViewContract: RepositoryListViewContract
    @Mock
    lateinit var mockGartRepository: GartRepository
    @Mock
    lateinit var mockUtils: Utils
    @Spy
    private val spyContext: Context = Robolectric.buildActivity(Activity::class.java).get()
    @Mock
    private lateinit var mockConnectivityManager: ConnectivityManager
    @Mock
    private lateinit var mockNetworkInfo: NetworkInfo

    @Before
    fun setUp() {
        initMocks(this)

        val mockSuccessResponseJsonObject = JsonParser().parse(MockSuccessResponse.data).asJsonObject
        successResponse = Response.success(mockSuccessResponseJsonObject)
        errorResponse = Response.error<JsonObject>(404, ResponseBody.create(MediaType.parse("application/json"), MockErrorResponse.data))

        `when`(mockApplication.applicationContext).thenReturn(spyContext)
        `when`(spyContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).thenReturn(mockConnectivityManager)
        `when`(mockConnectivityManager.activeNetworkInfo).thenReturn(mockNetworkInfo)
        `when`(mockUtils.isConnected(spyContext)).thenReturn(true)
        `when`(mockGartRepository.fetchGithubRepositories()).thenReturn(successResponse)

        viewModel = RepositoryListViewModel(mockApplication, mockViewContract, mockGartRepository, mockUtils)
    }

    @Test
    fun getGithubRepositories_when_the_viewModel_getGithubRepositories_is_invoked() {
        viewModel.getGithubRepositories()

        verify(mockGartRepository).getGithubRepositories()
    }

}