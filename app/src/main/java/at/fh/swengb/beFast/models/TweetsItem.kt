package at.fh.swengb.beFast.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class TweetsItem(

    val text: String,
    val created_at: String,
    val entities: Entities
    //val profile_image_url: String
){}

@JsonClass(generateAdapter = true)
data class Entities(
    val urls: List<Url>,
    val media: List<Media> = listOf<Media>(Media("https://upload.wikimedia.org/wikipedia/commons/f/fc/No_picture_available.png"))
)


@JsonClass(generateAdapter = true)
class Url(
    val url: String
    )


@JsonClass(generateAdapter = true)
class Media(
        val media_url_https: String
)


