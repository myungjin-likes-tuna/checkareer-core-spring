package com.mlt.checkareercorespring.domain.skill.model.entity

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("Skill")
class Skill(
    @Id
    val id: Long,
    val name: String
)