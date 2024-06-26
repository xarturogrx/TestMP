package com.arturogr.mytestmp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DetailMovie(
    val name: String,
    val image: String,
    val countVotes: Int,
    val votes: Double,
    val detail: String,
    val date: String,
    val gender: String,
    val classification: Boolean,
) : Parcelable
