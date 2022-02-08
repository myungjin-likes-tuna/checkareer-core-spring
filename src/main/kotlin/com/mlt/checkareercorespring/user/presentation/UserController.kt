package com.mlt.checkareercorespring.user.presentation

import com.mlt.checkareercorespring.user.application.UserService
import com.mlt.checkareercorespring.user.dto.UserNameGetResponse
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class UserController(
    private val userService: UserService
) {

    @GetMapping("/users-similar-skills")
    fun executeQuery(): ResponseEntity<List<UserNameGetResponse>> {
        return ResponseEntity.ok(UserNameGetResponse.listOf(userService.findUserBySkillGraphOrderBySimilarity()))
    }
}