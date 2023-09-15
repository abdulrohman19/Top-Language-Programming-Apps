package com.best.toplanguage

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collection(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
