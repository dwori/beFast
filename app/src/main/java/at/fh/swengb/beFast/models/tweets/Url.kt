package at.fh.swengb.beFast.models.tweets

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class Url(
        val url: String
)
