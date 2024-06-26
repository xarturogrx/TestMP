package com.arturogr.mytestmp.domain.repository

import com.arturogr.mytestmp.core.network.ApiService
import com.arturogr.mytestmp.domain.model.MovieResponse
import com.arturogr.mytestmp.domain.model.RequestSearch
import com.arturogr.mytestmp.domain.model.Response
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPopularMovies(request: RequestSearch): Response<MovieResponse> {
        Response.Loading
        return try {
            val result = apiService.getPopularMovies(request.apiKey, request.page)
            Response.Success(result)
        } catch (e: UnknownHostException) {
            Response.Failure(e)
        }
    }
}
