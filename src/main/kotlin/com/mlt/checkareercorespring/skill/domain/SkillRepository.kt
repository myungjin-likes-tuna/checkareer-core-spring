package com.mlt.checkareercorespring.skill.domain

import org.springframework.data.neo4j.repository.Neo4jRepository

interface SkillRepository : Neo4jRepository<Skill, Long>