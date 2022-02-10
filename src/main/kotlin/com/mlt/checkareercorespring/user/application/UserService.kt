package com.mlt.checkareercorespring.user.application

import com.mlt.checkareercorespring.user.domain.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService(
    private val userRepository: UserRepository
) {

    fun getUsersWithSimilarSkills(): List<String> {
        return userRepository.findUserNameBySkillGraphOrderBySimilarity()
    }
}