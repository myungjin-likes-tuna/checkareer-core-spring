package com.mlt.checkareercorespring.domain.user.service

import com.mlt.checkareercorespring.domain.common.model.Paging
import com.mlt.checkareercorespring.domain.skill.repository.SkillRepository
import com.mlt.checkareercorespring.domain.user.model.dto.UserSaveRequest
import com.mlt.checkareercorespring.domain.user.model.dto.UserSkillUpdateRequest
import com.mlt.checkareercorespring.domain.user.model.entity.User
import com.mlt.checkareercorespring.domain.user.repository.UserRepository
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import lombok.RequiredArgsConstructor       // lombok: spring 기능들 간단하게 사용하는 라이브러리
import org.neo4j.driver.internal.value.MapValue
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service

@AllArgsConstructor
@RequiredArgsConstructor        //
@NoArgsConstructor
class UserService(
    private val userRepository: UserRepository,
    private val skillRepository: SkillRepository
) {

    // 내부에서 트랜잭션 function 을 호출하면 트랜잭션 반영되지 않음
    @Transactional      // 작업의 완전성 보장
    fun createUser(userSaveRequest: UserSaveRequest) {
        userRepository.findById(userSaveRequest.id)
            .ifPresent { throw IllegalArgumentException("이미 존재하는 유저입니다. (userId: ${userSaveRequest.id})") }

        val skills = skillRepository.findAllById(userSaveRequest.skillIds)
        val user = User(userSaveRequest.id, userSaveRequest.name, skills)

        userRepository.save(user)

        dropAndCreateUserSkillGraph()
    }

    @Transactional
    fun updateUserSkills(userId: String, userSkillUpdateRequest: UserSkillUpdateRequest) {
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
    fun getUserSkillSimilarity(userId: String, paging: Paging): List<MapValue> {
        return userRepository.findUserSkillSimilarityBySkillGraphOrderBySkillSimilarityDesc(
            userId,
            paging.sortOrder,
            paging.limit
        )
    }
}