package com.mlt.checkareercorespring.user.application

import com.mlt.checkareercorespring.common.Paging
import com.mlt.checkareercorespring.user.domain.UserRepository
import lombok.RequiredArgsConstructor
import org.neo4j.driver.internal.value.MapValue
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService(
    private val userRepository: UserRepository
) {

    fun getUserSkillSimilarity(userId: Long, paging: Paging): List<MapValue> {
        return userRepository.findUserSkillSimilarityBySkillGraphOrderBySimilarityDesc(
            userId,
            paging.sortOrder,
            paging.limit
        )
    }
}