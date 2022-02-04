package com.mlt.checkareercorespring.person.dto

data class PersonNameGetResponse(
    val name: String
) {
    companion object {
        fun listOf(people: List<Any>): List<PersonNameGetResponse> {
            return people.map { of(it.toString()) }.toList()
        }

        private fun of(name: String): PersonNameGetResponse {
            return PersonNameGetResponse(name)
        }
    }
}