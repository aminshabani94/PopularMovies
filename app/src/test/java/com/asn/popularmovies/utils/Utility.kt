package com.asn.popularmovies.utils

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf

// Utility function to collect data from PagingData
suspend fun <T : Any> PagingData<T>.collectData(): List<T> {
    val items = mutableListOf<T>()
    val flow = flowOf(this)
    flow.collectLatest { pagingData ->
        pagingData.map { item ->
            items.add(item)
        }
    }
    return items
}