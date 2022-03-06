package com.mlt.checkareercorespring.skill.application

import com.mlt.checkareercorespring.skill.domain.Skill
import com.mlt.checkareercorespring.skill.domain.SkillRepository
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