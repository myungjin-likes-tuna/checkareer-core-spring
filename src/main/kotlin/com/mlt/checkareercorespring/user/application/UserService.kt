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
        userRepository.findById(userSaveRequest.id)
            .ifPresent { throw IllegalArgumentException("이미 존재하는 유저입니다. (userId: ${userSaveRequest.id})") }

        val skills = skillRepository.findAllById(userSaveRequest.skillIds)
        val user = User(userSaveRequest.id, userSaveRequest.name, skills)
        userRepository.save(user)

        dropAndCreateUserSkillGraph()
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
        dropAndCreateUserSkillGraph()
    }

    private fun dropAndCreateUserSkillGraph() {
        val isExistUserSkillGraph = userRepository.isExistUserSkillGraph()

        if (isExistUserSkillGraph) {
            dropUserSkillGraph()
        }

        createUserSkillGraph()
    }

    private fun dropUserSkillGraph() {
        try {
            userRepository.dropUserSkillGraph()
        } catch (e: Exception) {
//            println("drop 완료")
            // dropUserSkillGraph() 리턴 결과를 매핑 안해줘서 exception 생기므로 예외처리
        }
    }

    private fun createUserSkillGraph() {
        try {
            userRepository.createUserSkillGraph()
        } catch (e: Exception) {
//            println("create 완료")
            // dropUserSkillGraph() 리턴 결과를 매핑 안해줘서 exception 생기므로 예외처리
        }
    }

    @Transactional(readOnly = true)
    fun getUserSkillSimilarity(userId: Long, paging: Paging): List<MapValue> {
        return userRepository.findUserSkillSimilarityBySkillGraphOrderBySkillSimilarityDesc(
            userId,
            paging.sortOrder,
            paging.limit
        )
    }
}