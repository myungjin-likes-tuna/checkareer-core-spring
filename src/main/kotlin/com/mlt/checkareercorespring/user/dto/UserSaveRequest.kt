package com.mlt.checkareercorespring.user.dto

data class UserSaveRequest(
    val id: Long,
    val name: String,
    val skills: Set<Long>
)