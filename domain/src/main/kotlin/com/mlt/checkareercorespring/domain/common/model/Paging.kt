package com.mlt.checkareercorespring.domain.common.model

data class Paging(
    val limit: Int? = DEFAULT_LIMIT,
    val sortOrder: SortOrder = SortOrder.DESC
) {
    companion object {
        private const val DEFAULT_LIMIT = 10
    }

    enum class SortOrder {
        ASC, DESC
    }
}