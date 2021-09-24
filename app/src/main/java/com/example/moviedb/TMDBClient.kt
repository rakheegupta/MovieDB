package com.example.moviedb

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import org.json.JSONArray

class TMDBClient {
    val movieClient=AsyncHttpClient()
    fun fetchPopularMovies(handler: JsonHttpResponseHandler)
    {
        val params= RequestParams(R.string.api_key_name,R.string.api_key)
        movieClient.get(R.string.api_base_url.toString(),params,handler)
    }
}