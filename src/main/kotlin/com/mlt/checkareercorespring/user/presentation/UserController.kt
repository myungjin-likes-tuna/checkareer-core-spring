package com.mlt.checkareercorespring.user.presentation

import com.mlt.checkareercorespring.common.Paging
import com.mlt.checkareercorespring.user.application.UserService
import com.mlt.checkareercorespring.user.dto.UserSkillSimilarityGetResponse
import lombok.RequiredArgsConstructor
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class UserController(
    private val userService: UserService
) {

    @GetMapping("/users/{userId}/skill-similarity", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserSkillSimilarity(
        @PathVariable("userId") userId: Long,
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