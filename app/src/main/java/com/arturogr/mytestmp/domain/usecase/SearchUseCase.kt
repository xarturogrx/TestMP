package com.arturogr.mytestmp.domain.usecase

import com.arturogr.mytestmp.core.Constants
import com.arturogr.mytestmp.domain.model.MovieResponse
import com.arturogr.mytestmp.domain.model.RequestSearch
import com.arturogr.mytestmp.domain.model.Response
import com.arturogr.mytestmp.domain.model.ValidationException
import com.arturogr.mytestmp.domain.repository.MovieRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(request: RequestSearch): Response<MovieResponse> {
        val responseFinal: Response<MovieResponse>
        when (val response = repository.getPopularMovies(request)) {
            is Response.Success -> {
                val data = response.data.results
                responseFinal = if (data.isNotEmpty()) {
                    response
                } else {
                    Response.Failure(ValidationException.ServerException(Constants.EMPTY_LIST))
                }
            }

            is Response.Failure -> {
                val exception = response.exception
                    ?: ValidationException.ServerException(Constants.SERVER_FAILURE)
                responseFinal = Response.Failure(exception)
            }

            else -> {
                responseFinal =
                    Response.Failure(ValidationException.ServerException(Constants.EMPTY_LIST))
            }
        }
        return responseFinal
    }
}
