package com.mlt.checkareercorespring.user.presentation

import com.mlt.checkareercorespring.user.application.UserService
import com.mlt.checkareercorespring.user.dto.UserSkillSimilarityGetResponse
import lombok.RequiredArgsConstructor
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class UserController(
    private val userService: UserService
) {

    @GetMapping("/users-skill-similarity", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserSkillSimilarity(): ResponseEntity<List<UserSkillSimilarityGetResponse>> {
        return ResponseEntity.ok(UserSkillSimilarityGetResponse.listOf(userService.getUserSkillSimilarity()))
    }
}