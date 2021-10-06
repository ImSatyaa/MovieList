package com.satyam.movielist.network

import com.satyam.movielist.di.NetworkModule
import com.satyam.movielist.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieAPIService {

    @GET("?apiKey=" + NetworkModule.API_KEY)
    suspend fun getMovies(@Query("s") search : String ="fast",
                    @Query("page") page : Int = 1): MovieResponse
}