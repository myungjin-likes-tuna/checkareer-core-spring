package com.mlt.checkareercorespring.controller

import com.mlt.checkareercorespring.domain.skill.model.dto.SkillGetResponse
import com.mlt.checkareercorespring.domain.skill.service.SkillService
import lombok.RequiredArgsConstructor
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class SkillController(
    private val skillService: SkillService
) {

    @GetMapping("/skills", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllSkills(): ResponseEntity<List<SkillGetResponse>> {
        return ResponseEntity.ok(SkillGetResponse.listOf(skillService.getAllSkills()))
    }
}