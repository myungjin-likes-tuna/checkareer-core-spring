package com.mlt.checkareercorespring.domain.skill.service

import com.mlt.checkareercorespring.domain.skill.model.entity.Skill
import com.mlt.checkareercorespring.domain.skill.repository.SkillRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class SkillService(
    private val skillRepository: SkillRepository
) {

    @Transactional(readOnly = true)
    fun getAllSkills(): List<Skill> {
        return skillRepository.findAll()
    }
}