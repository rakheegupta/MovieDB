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


}

/*client.interceptors().add(object: Interceptor{
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val request: Request = chain.request()
                val url:HttpUrl = request.url()
                    .newBuilder()
                    .addQueryParameter("api_key",api_key)
                    .build()

                val newRequest:Request = chain.request().newBuilder().url(url).build()
                return chain.proceed(newRequest)
            }

        })*/