package com.mlt.checkareercorespring.user.application

import com.mlt.checkareercorespring.common.Paging
import com.mlt.checkareercorespring.skill.domain.SkillRepository
import com.mlt.checkareercorespring.user.domain.User
import com.mlt.checkareercorespring.user.domain.UserRepository
import com.mlt.checkareercorespring.user.dto.UserSaveRequest
import com.mlt.checkareercorespring.user.dto.UserSkillUpdateRequest
import lombok.RequiredArgsConstructor
import org.neo4j.driver.internal.value.MapValue
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class UserService(
    private val userRepository: UserRepository,
    private val skillRepository: SkillRepository
) {

    @Transactional
    fun createUser(userSaveRequest: UserSaveRequest) {
        userRepository.findById(userSaveRequest.userId)
            .ifPresent { throw IllegalArgumentException("이미 존재하는 유저입니다. (userId: ${userSaveRequest.userId})") }

        val skills = skillRepository.findAllById(userSaveRequest.skillIds)
        val user = User(userSaveRequest.userId, userSaveRequest.name, skills)
        userRepository.save(user)
    }

    @Transactional
    fun updateUserSkills(userId: Long, userSkillUpdateRequest: UserSkillUpdateRequest) {
        val user = userRepository.findById(userId).orElseThrow {
            throw IllegalArgumentException("존재하지 않는 유저입니다. (userId: ${userId})")
        }
        userRepository.deleteSkillRelationship(user.id)

        val newSkills = skillRepository.findAllById(userSkillUpdateRequest.skillIds)
        user.skills = newSkills
        userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun getUserSkillSimilarity(userId: Long, paging: Paging): List<MapValue> {
        return userRepository.findUserSkillSimilarityBySkillGraphOrderBySimilarityDesc(
            userId,
            paging.sortOrder,
            paging.limit
        )
    }
}