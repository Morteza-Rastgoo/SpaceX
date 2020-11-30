package com.dynamo.spacex.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author : Morteza Rastgoo
 * @since : 30/11/2020 AD
 **/
object DateUtils {
    //Todo: create a typeAlias for DateString
    //Todo: Apply dynamic locale
    private val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    private val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.US)

    /**
     * A simple function to parse the date to human readable format
     */
    fun parseDate(stringDate: String?): String {
        return try {
            formatter.format(parser.parse(stringDate))
        } catch (e: Exception) {
            stringDate ?: ""
        }
    }
}