package com.example.fitlife.data.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(
    val name: String,
    val photo: String,
    val desc: String
) : Parcelable