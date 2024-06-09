package com.asn.popularmovies.data.model.configuration

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Configuration(
    val images: Images?,
    val isFromDb: Boolean = false
): Parcelable