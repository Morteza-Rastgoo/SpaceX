package com.dynamo.spacex.util

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @author : Morteza Rastgoo
 * @since : 30/11/2020 AD
 **/

object YoutubeUtils {
    /**
     * Get youtube id from url
     */
    fun getYouTubeId(url: String): String? {
        return try {
            val pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed/)[^#&?]*"
            val compiledPattern: Pattern = Pattern.compile(pattern)
            val matcher: Matcher = compiledPattern.matcher(url)
            if (matcher.find()) {
                matcher.group()
            } else {
                null
            }
        } catch (e: Exception) {
            //Throw exception for invalid url
            null
        }
    }
}