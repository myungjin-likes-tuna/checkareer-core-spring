package com.mlt.checkareercorespring.person.presentation

import com.mlt.checkareercorespring.person.application.PersonService
import com.mlt.checkareercorespring.person.dto.PersonNameGetResponse
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class PersonController(
    private val personService: PersonService
) {

    @GetMapping("/execute-custom-query")
    fun executeQuery(): ResponseEntity<List<PersonNameGetResponse>> {
        return ResponseEntity.ok(PersonNameGetResponse.listOf(personService.executeCustomQuery()))
    }
}