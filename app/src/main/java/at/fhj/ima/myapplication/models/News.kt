package at.fhj.ima.myapplication.models

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class Article(
    val text: String,
    val created_at: String
    // val entitites: Entities
    // val imageUrl: String
){}
/*@JsonClass(generateAdapter = true)
class Entities(val urls: List<Urls>, val media: List<Media>)

@JsonClass(generateAdapter = true)
class Urls(val url: String)

@JsonClass(generateAdapter = true)
class Media(val media_url_https: String)*/




