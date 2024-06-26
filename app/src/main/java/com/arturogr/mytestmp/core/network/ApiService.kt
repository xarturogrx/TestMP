package com.arturogr.mytestmp.core.network

import com.arturogr.mytestmp.BuildConfig
import com.arturogr.mytestmp.domain.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(BuildConfig.URL_MOVIE)
    suspend fun getPopularMovies(
        @Query(BuildConfig.P_API_KEY) apiKey: String = BuildConfig.V_API_KEY,
        @Query("page") page: Int
    ): MovieResponse
}
