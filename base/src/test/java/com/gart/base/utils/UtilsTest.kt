package com.gart.base.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.Spy
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE, sdk = [21])
class UtilsTest {

    @Spy
    private val spyContext: Context = Robolectric.buildActivity(Activity::class.java).get()
    @Mock
    private lateinit var mockConnectivityManager: ConnectivityManager
    @Mock
    private lateinit var mockNetworkInfo: NetworkInfo

    @Before
    fun setUp() {
        initMocks(this)
    }

    @Test
    fun isConnected_should_return_false_if_networkInfo_is_null() {
        `when`(spyContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).thenReturn(mockConnectivityManager)
        `when`(mockConnectivityManager.activeNetworkInfo).thenReturn(null)

        assertFalse(Utils.isConnected(spyContext))
    }

    @Test
    fun isConnected_should_return_false_if_networkInfo_isConnectedOrConnecting_returns_false() {
        `when`(spyContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).thenReturn(mockConnectivityManager)
        `when`(mockConnectivityManager.activeNetworkInfo).thenReturn(mockNetworkInfo)
        `when`(mockNetworkInfo.isConnectedOrConnecting).thenReturn(false)

        assertFalse(Utils.isConnected(spyContext))
    }

    @Test
    fun isConnected_should_return_true_if_networkInfo_is_not_null_and_isConnectedOrConnecting_returns_true() {
        `when`(spyContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).thenReturn(mockConnectivityManager)
        `when`(mockConnectivityManager.activeNetworkInfo).thenReturn(mockNetworkInfo)
        `when`(mockNetworkInfo.isConnectedOrConnecting).thenReturn(true)

        assertTrue(Utils.isConnected(spyContext))
    }

}