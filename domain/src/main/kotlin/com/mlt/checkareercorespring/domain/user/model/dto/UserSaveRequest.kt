package com.mlt.checkareercorespring.domain.user.model.dto

data class UserSaveRequest(
    val id: Long,
    val name: String,
    val skillIds: Set<Long>
)