package at.fh.swengb.beFast.news.recylcerview


import at.fh.swengb.beFast.api.TwitterApi
import at.fh.swengb.beFast.models.TweetsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object TweetRepository {

    fun tweetList(success: (articleList: List<TweetsItem>) -> Unit, error: (errorMessage: String) -> Unit) {
        TwitterApi.retrofitService.getTweetList().enqueue(object:
                Callback<List<TweetsItem>> {
            override fun onFailure(call: Call<List<TweetsItem>>, t: Throwable) {
                error("The call failed")
            }

            override fun onResponse(call: Call<List<TweetsItem>>, response: Response<List<TweetsItem>>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }

        })
    }

}