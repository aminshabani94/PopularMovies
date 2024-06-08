package com.asn.popularmovies.data.model.configuration

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    @SerializedName("base_url")
    val baseUrl: String?,
    @SerializedName("secure_base_url")
    val secureBaseUrl: String?,
): Parcelable