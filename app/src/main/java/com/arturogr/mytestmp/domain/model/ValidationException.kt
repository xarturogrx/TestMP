package com.arturogr.mytestmp.domain.model

sealed class ValidationException(override val message: String) : Exception(message) {
    class ServerException(message: String) : ValidationException(message)
}
