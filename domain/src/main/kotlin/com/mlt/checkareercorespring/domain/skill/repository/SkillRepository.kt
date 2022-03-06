package com.mlt.checkareercorespring.domain.skill.repository

import com.mlt.checkareercorespring.domain.skill.model.entity.Skill
import org.springframework.data.neo4j.repository.Neo4jRepository

interface SkillRepository : Neo4jRepository<Skill, Long>