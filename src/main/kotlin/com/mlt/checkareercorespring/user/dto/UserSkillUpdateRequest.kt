package com.mlt.checkareercorespring.user.dto

data class UserSkillUpdateRequest(
    val userId: Long,
    val skillIds: Set<Long>
)