package com.example.moviedb

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val api_key:String = "69694a19e98df7f5c79b13285d536102"

interface TMDBService {

    @GET("movie/now_playing")
    fun listMovies(): Call<TMDBMovieResponse>

    companion object {

        operator fun invoke():TMDBService{

            val requestInterceptor = Interceptor{chain->
                val url: HttpUrl = chain.request().url()
                    .newBuilder()
                    .addQueryParameter("api_key",api_key)
                    .build()

                val newRequest: Request = chain.request().newBuilder().url(url).build()
                return@Interceptor chain.proceed(newRequest)
            }

            val client=
                OkHttpClient.Builder().addInterceptor(requestInterceptor).build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TMDBService::class.java)
        }
    }
}