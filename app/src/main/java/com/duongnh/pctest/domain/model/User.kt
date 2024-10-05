package com.duongnh.pctest.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val age: Int,
    val imageUrl: String,
    val bio: String
) : Parcelable {
    constructor() : this(0, "", 0, "", "")
}