package com.mlt.checkareercorespring.skill.dto

import com.mlt.checkareercorespring.skill.domain.Skill

data class SkillGetResponse(
    val id: Long,
    val name: String,
) {
    companion object {
        fun listOf(skills: List<Skill>): List<SkillGetResponse> {
            return skills.map { of(it) }
        }

        fun of(skill: Skill): SkillGetResponse {
            return SkillGetResponse(skill.id, skill.name)
        }
    }
}