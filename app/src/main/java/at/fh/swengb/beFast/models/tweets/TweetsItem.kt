package at.fh.swengb.beFast.models.tweets

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class TweetsItem(
        val text: String,
        val created_at: String,
        val entities: Entities
)