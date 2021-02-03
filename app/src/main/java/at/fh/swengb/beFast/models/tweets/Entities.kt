package at.fh.swengb.beFast.models.tweets

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Entities(
        val urls: List<Url>,
        /** default value when there is no media-link (no picture is shown) */
        val media: List<Media> = listOf(Media(" "))
)