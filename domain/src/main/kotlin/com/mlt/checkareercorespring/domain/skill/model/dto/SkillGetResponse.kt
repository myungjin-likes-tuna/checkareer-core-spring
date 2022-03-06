package com.mlt.checkareercorespring.domain.skill.model.dto

import com.mlt.checkareercorespring.domain.skill.model.entity.Skill

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