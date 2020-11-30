package com.dynamo.spacex.data.network.model.pastlaunch

data class Links(
    val flickr_images: List<String>? = null,
    val mission_patch_small: String = "", // https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png
    val video_link: String = "", // https://www.youtube.com/watch?v=0a_00nJ_Y88
    val youtube_id: String = "" // 0a_00nJ_Y88
)