package at.fh.swengb.beFast.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class TweetsItem(
    val text: String,
    val created_at: String
){}

//TODO: test

//TODO: test