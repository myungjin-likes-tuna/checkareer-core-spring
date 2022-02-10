package com.mlt.checkareercorespring.skill.domain

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("Skill")
class Skill(
    @Id @GeneratedValue
    val id: Long,
    val name: String
)