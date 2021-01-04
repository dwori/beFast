package at.fh.swengb.beFast.api


import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object TwitterApi {
    // fill in your access token and base url
    const val accessToken = "AAAAAAAAAAAAAAAAAAAAAJkxKwEAAAAAAe17jtzpFcF%2FJ18O8QoxfW3ToPU%3D6PAygRGoLojJ24cAHPQQByzv5ziPv1hHU66JWTnvEGQx6uzOme"
    val retrofit: Retrofit
    val retrofitService: TwitterApiService
    init {
        val moshi = Moshi.Builder().build()
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.twitter.com/")
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer $accessToken").build()
                chain.proceed(request)}.build())
            .build()
        retrofitService = retrofit.create(TwitterApiService::class.java)
    }
}