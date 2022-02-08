package com.mlt.checkareercorespring.user.dto

data class UserNameGetResponse(
    val name: String
) {
    companion object {
        fun listOf(users: List<Any>): List<UserNameGetResponse> {
            return users.map { of(it.toString()) }.toList()
        }

        private fun of(name: String): UserNameGetResponse {
            return UserNameGetResponse(name)
        }
    }
}