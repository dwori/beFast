package at.fh.swengb.beFast.api

import at.fh.swengb.beFast.models.tweets.TweetsItem
import retrofit2.Call
import retrofit2.http.GET

// make the actual api call and defining what you want to GET
interface TwitterApiService {
    @GET("1.1/lists/statuses.json?slug=Sneaker&owner_screen_name=Laeuft_bei_mir&count=50&include_rts=false")
    fun getTweetList(): Call<List<TweetsItem>>
}