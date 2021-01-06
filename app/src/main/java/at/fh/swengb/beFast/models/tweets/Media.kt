package at.fh.swengb.beFast.models.tweets

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class Media(
        val media_url_https: String
)