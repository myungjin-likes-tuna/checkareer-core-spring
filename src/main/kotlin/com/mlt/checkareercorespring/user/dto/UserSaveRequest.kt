package com.mlt.checkareercorespring.user.dto

data class UserSaveRequest(
    val id: Long,
    val name: String,
    val skillIds: Set<Long>
)