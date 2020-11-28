package com.dynamo.spacex.data.model.pastlaunch

data class Links(
    val article_link: String, // https://www.space.com/2196-spacex-inaugural-falcon-1-rocket-lost-launch.html
    val flickr_images: List<Any>,
    val mission_patch: String, // https://images2.imgbox.com/40/e3/GypSkayF_o.png
    val mission_patch_small: String, // https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png
    val presskit: Any?, // null
    val reddit_campaign: Any?, // null
    val reddit_launch: Any?, // null
    val reddit_media: Any?, // null
    val reddit_recovery: Any?, // null
    val video_link: String, // https://www.youtube.com/watch?v=0a_00nJ_Y88
    val wikipedia: String, // https://en.wikipedia.org/wiki/DemoSat
    val youtube_id: String // 0a_00nJ_Y88
)