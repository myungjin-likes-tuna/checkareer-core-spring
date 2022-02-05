package com.mlt.checkareercorespring.person.application

import com.mlt.checkareercorespring.person.domain.PersonRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class PersonService(
    private val personRepository: PersonRepository
) {

    fun executeCustomQuery(): List<Any> {
        return personRepository.executeCustomQuery()
    }
}