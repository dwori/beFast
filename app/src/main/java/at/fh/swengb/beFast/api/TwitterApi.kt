package at.fh.swengb.beFast.api


import at.fh.swengb.beFast.models.tweets.TweetsItem
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object TwitterApi {
    /**
     * providing the bearer token in order to connect with the twitter api
     * request and build the connection
     */
    private const val accessToken = "AAAAAAAAAAAAAAAAAAAAAJkxKwEAAAAAAe17jtzpFcF%2FJ18O8QoxfW3ToPU%3D6PAygRGoLojJ24cAHPQQByzv5ziPv1hHU66JWTnvEGQx6uzOme"
    private val retrofit: Retrofit
    private val retrofitService: TwitterApiService

    init {
        val moshi = Moshi.Builder().build()
        retrofit = Retrofit
            .Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.twitter.com/")
            .client(
                OkHttpClient.Builder().addInterceptor { chain ->
                    val request = chain
                        .request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer $accessToken")
                        .build()
                    chain.proceed(request)
                }.build()
            )
            .build()
        retrofitService = retrofit.create(TwitterApiService::class.java)
    }

    /**
     *
     * tweetList takes 2 parameters and returns either the data of the api-call or an error
     * @param success takes a lambda expression and returns a List of TweetItem in the method onResponse (if API-Call successful)
     * @param error takes a lambda expression and returns an Error Message in the method inFailure (if API-Call failed)
     *
     *
     */
    fun tweetList(success: (articleList: List<TweetsItem>) -> Unit, error: (errorMessage: String) -> Unit) {
        retrofitService.getTweetList().enqueue(
            object: Callback<List<TweetsItem>> {
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
            }
        )
    }
}