package com.example.moviedb

import android.app.TaskStackBuilder.create
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class TMDBClient {
    /*val movieClient= AsyncHttpClient()
    fun fetchPopularMovies(handler: JsonHttpResponseHandler)
    {
        val params= RequestParams()
        //params.put(R.string.api_key_name,R.string.api_key)
        movieClient.get(R.string.api_base_url.toString(),params,handler)
    }*/
    val client=OkHttpClient()

    fun fetchPopularMovies(){

        client.interceptors().add(object: Interceptor{
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val request: Request = chain.request()
                val url:HttpUrl = request.url().newBuilder().addQueryParameter(R.string.api_key_name.toString(),R.string.api_key.toString()).build()

                val newRequest:Request = chain.request().newBuilder().url(url).build()
                return chain.proceed(newRequest)
            }

        })
        val retrofitClient:Retrofit=Retrofit.Builder()
            .client(client)
            .baseUrl(R.string.api_full_url.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService:TMDBService = retrofitClient.create(TMDBService::class.java)
        val call:Call <TMDBMovieResponse> = apiService.listMovies()

        call.enqueue(object: Callback<TMDBMovieResponse> {
            override fun onResponse(call: Call<TMDBMovieResponse>, response: Response<TMDBMovieResponse>) {
                // handle response here
                val tmdbMovieResponse: TMDBMovieResponse? = response.body()
                print("reached success")
            }

            override fun onFailure(call: Call<TMDBMovieResponse>, t: Throwable?) {
                print("reached faliure")
            }
        })
    }
}