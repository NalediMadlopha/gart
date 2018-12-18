package com.gart.base.utils

import android.content.Context
import android.net.ConnectivityManager
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

open class Utils {

    private val suffix = charArrayOf('k', 'm', 'b', 't')

    fun isConnected(context: Context): Boolean {
        val connectivityManager = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)
        val networkInfo = connectivityManager?.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    fun elapseTime(dateString: String): CharSequence {
        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT)
        simpleDateFormat.timeZone = TimeZone.getTimeZone(TIME_ZONE)

        val now = simpleDateFormat.parse(dateString)

        return DateUtils.getRelativeTimeSpanString(now.time)
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

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val TIME_ZONE = "UTC"
    }

}