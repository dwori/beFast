package at.fhj.ima.myapplication.sampledata

import at.fhj.ima.myapplication.adapter.TwitterApi
import at.fhj.ima.myapplication.models.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ArticleRepository {

    fun articleList(success: (articleList: List<Article>) -> Unit, error: (errorMessage: String) -> Unit) {
        TwitterApi.retrofitService.lessons().enqueue(object:
            Callback<List<Article>> {
            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                error("The call failed")
            }

            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
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