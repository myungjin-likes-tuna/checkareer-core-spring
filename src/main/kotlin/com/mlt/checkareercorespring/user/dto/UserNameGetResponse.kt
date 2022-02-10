package com.mlt.checkareercorespring.user.dto

data class UserNameGetResponse(
    val name: String
) {
    companion object {
        fun listOf(userNames: List<String>): List<UserNameGetResponse> {
            return userNames.map { of(it) }.toList()
        }

        private fun of(name: String): UserNameGetResponse {
            return UserNameGetResponse(name)
        }
    }
}