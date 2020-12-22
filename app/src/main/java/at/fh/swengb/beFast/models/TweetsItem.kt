package at.fh.swengb.beFast.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class TweetsItem(
    val text: String,
    val entities: Entities
){}

@JsonClass(generateAdapter = true)
data class Entities(
    val urls: List<Url>
)

@JsonClass(generateAdapter = true)
class Url(
    val url: String
    )
