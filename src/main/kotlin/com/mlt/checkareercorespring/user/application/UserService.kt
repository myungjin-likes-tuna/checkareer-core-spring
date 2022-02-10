package com.mlt.checkareercorespring.user.application

import com.mlt.checkareercorespring.user.domain.UserRepository
import lombok.RequiredArgsConstructor
import org.neo4j.driver.internal.value.MapValue
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService(
    private val userRepository: UserRepository
) {

    fun getUserSkillSimilarity(): List<MapValue> {
        return userRepository.findUserSkillSimilarityBySkillGraphOrderBySimilarityDesc()
    }
}