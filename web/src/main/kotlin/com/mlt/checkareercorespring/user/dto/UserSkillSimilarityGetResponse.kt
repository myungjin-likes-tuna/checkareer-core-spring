package com.mlt.checkareercorespring.user.dto

import org.neo4j.driver.internal.value.MapValue

data class UserSkillSimilarityGetResponse(
    var user: UserGetResponse,
    var skillSimilarity: Double
) {
    companion object {
        fun listOf(userSkillSimilarities: List<MapValue>): List<UserSkillSimilarityGetResponse> {
            return userSkillSimilarities.map { of(it) }.toList()
        }

        // TODO: 해당 key 를 가진 노드가 없으면 제외시키기
        private fun of(userSkillSimilarity: MapValue): UserSkillSimilarityGetResponse {
            return UserSkillSimilarityGetResponse(
                UserGetResponse(userSkillSimilarity["user"]["id"].asLong(), userSkillSimilarity["user"]["name"].asString()),
                userSkillSimilarity["skillSimilarity"].asDouble()
            )
        }
    }
}