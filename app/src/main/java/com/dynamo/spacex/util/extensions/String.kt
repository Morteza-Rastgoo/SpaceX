package com.dynamo.spacex.util.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @author : Morteza Rastgoo
 * @since : 29/11/2020 AD
 **/

/**
 * A simple function to parse the date to human readable format
 */
fun String?.parseDate(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.US)
    var date: String
    date = try {
        formatter.format(parser.parse(this))
    } catch (e: Exception) {
        this ?: ""
    }
    return date
}

/**
 * Get youtube id from url
 */
fun String?.getYouTubeId(): String? {
    return try {
        val pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed/)[^#&?]*"
        val compiledPattern: Pattern = Pattern.compile(pattern)
        val matcher: Matcher = compiledPattern.matcher(this!!)
        if (matcher.find()) {
            matcher.group()
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}
