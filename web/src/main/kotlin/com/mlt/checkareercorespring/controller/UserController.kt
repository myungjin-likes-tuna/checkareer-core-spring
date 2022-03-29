package com.mlt.checkareercorespring.controller

import com.mlt.checkareercorespring.annotation.CheckPermission
import com.mlt.checkareercorespring.domain.common.model.Paging
import com.mlt.checkareercorespring.domain.user.model.dto.UserSaveRequest
import com.mlt.checkareercorespring.domain.user.model.dto.UserSkillSimilarityGetResponse
import com.mlt.checkareercorespring.domain.user.model.dto.UserSkillUpdateRequest
import com.mlt.checkareercorespring.domain.user.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

// TODO: 예외 상황에 따라 response 규격 정하기
@RestController
@RequiredArgsConstructor
class UserController(
    private val userService: UserService
) {

    @CheckPermission
    @PostMapping("/users", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createUser(@RequestBody userSaveRequest: UserSaveRequest): ResponseEntity<Void> {
        userService.createUser(userSaveRequest)
        return ResponseEntity(HttpStatus.OK)
    }

    @CheckPermission
    @PutMapping("/users/{userId}/skills", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateUserSkills(
        @PathVariable("userId") userId: String,
        @RequestBody userSkillUpdateRequest: UserSkillUpdateRequest
    ): ResponseEntity<Void> {
        userService.updateUserSkills(userId, userSkillUpdateRequest)

        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/users/{userId}/skill-similarity", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserSkillSimilarity(
        @PathVariable("userId") userId: String,
        paging: Paging
    ): ResponseEntity<List<UserSkillSimilarityGetResponse>> {
        return ResponseEntity.ok(
            UserSkillSimilarityGetResponse.listOf(
                userService.getUserSkillSimilarity(
                    userId,
                    paging
                )
            )
        )
    }
}