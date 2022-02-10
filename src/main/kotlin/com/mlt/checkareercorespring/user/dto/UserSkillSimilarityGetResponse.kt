package com.mlt.checkareercorespring.user.dto

import org.neo4j.driver.internal.value.MapValue

data class UserSkillSimilarityGetResponse(
    var user1: String,
    var user2: String,
    var similarity: Double
) {
    companion object {
        fun listOf(userSkillSimilarities: List<MapValue>): List<UserSkillSimilarityGetResponse> {
            return userSkillSimilarities.map { of(it) }.toList()
        }

        private fun of(userSkillSimilarity: MapValue): UserSkillSimilarityGetResponse {
            return UserSkillSimilarityGetResponse(
                userSkillSimilarity["user1"].asString(),
                userSkillSimilarity["user2"].asString(),
                userSkillSimilarity["similarity"].asDouble()
            )
        }
    }
}