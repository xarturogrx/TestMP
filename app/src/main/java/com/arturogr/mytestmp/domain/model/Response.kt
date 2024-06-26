package com.arturogr.mytestmp.domain.model

/**
 * Represent a Response in Search service
 */
sealed class Response<out T> {
    /**
     * Represent that you are in the Loading search
     */
    object Loading : Response<Nothing>()

    /**
     * Represent that you are receiving response service success
     * @property data The data response
     */
    data class Success<out T>(val data: T) : Response<T>()

    /**
     * Represent that you are receiving response service failure
     * @property exception The exception response
     */
    data class Failure<out T>(val exception: Exception?) : Response<T>()
}
