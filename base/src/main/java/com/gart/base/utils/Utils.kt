package com.gart.base.utils

import android.content.Context
import android.net.ConnectivityManager

open class Utils constructor() {

    private val suffix = charArrayOf('k', 'm', 'b', 't')

    fun isConnected(context: Context): Boolean {
        val connectivityManager = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)
        val networkInfo = connectivityManager?.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    fun numberSuffixConverter(valueToConvert: Double?, iteration: Int): String {
        val d = valueToConvert!!.toLong() / 100 / 10.0
        val isRound = d * 10 % 10 == 0.0

        return (if (d < 1000) {
            ((if (d > 99.9 || isRound || (!isRound && d > 9.99)) {
                d.toInt().times(10) / 10
            } else {
                (d).toString().plus("")
            })).toString() + "" + suffix[iteration]
        } else
            numberSuffixConverter(d, iteration + 1))

    }

}