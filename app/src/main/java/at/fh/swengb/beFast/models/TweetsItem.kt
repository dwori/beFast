package at.fh.swengb.beFast.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class TweetsItem(

    val text: String,
    val created_at: String,
    val entities: Entities
){}

@JsonClass(generateAdapter = true)
data class Entities(
    val urls: List<Url>
   // val media: List<Media>
)

@JsonClass(generateAdapter = true)
class Url(
    val url: String
    )



// TODO find the actual model for pictures
/*
@JsonClass(generateAdapter = true)
class Media(
        val media_url_https: String
)
*/