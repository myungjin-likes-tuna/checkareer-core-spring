package com.mlt.checkareercorespring.user.domain

data class UserSkillSimilarity(
    var user1: String,
    var user2: String,
    var similarity: Double
)