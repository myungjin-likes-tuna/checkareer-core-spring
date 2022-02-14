package com.mlt.checkareercorespring.common

import com.mlt.checkareercorespring.common.Paging.SortOrder.DESC

data class Paging(
    val limit: Int? = DEFAULT_LIMIT,
    val sortOrder: SortOrder = DESC
) {
    companion object {
        private const val DEFAULT_LIMIT = 10
    }

    enum class SortOrder {
        ASC, DESC
    }
}