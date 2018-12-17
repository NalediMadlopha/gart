package com.gart.base.utils

import android.content.Context
import android.net.ConnectivityManager

open class Utils {

    private val suffix = charArrayOf('k', 'm', 'b', 't')

    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    fun numberSuffixConverter(valueToConvert: Double?, iteration: Int): String {
        val convertedValue = (valueToConvert?.toLong() ?: 0) / 100 / 10.0
        val isRound = convertedValue * 10 % 10 == 0.0

        return (if (convertedValue < 1000) {
            ((if (convertedValue > 99.9 || isRound || (!isRound && convertedValue > 9.99)) {
                convertedValue.toInt().times(10) / 10
            } else {
                (convertedValue).toString().plus("")
            })).toString() + "" + suffix[iteration]
        } else
            numberSuffixConverter(convertedValue, iteration + 1))
    }

}