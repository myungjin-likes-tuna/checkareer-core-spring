package com.mlt.checkareercorespring.user.dto

data class UserSaveRequest(
    val userId: Long,
    val name: String,
    val skillIds: Set<Long>
)