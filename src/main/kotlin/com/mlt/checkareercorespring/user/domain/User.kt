package com.mlt.checkareercorespring.user.domain

import com.mlt.checkareercorespring.skill.domain.Skill
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.util.*

@Node("User")
class User(
    @Id
    val id: Long,
    val name: String,

    @Relationship(type = "likes", direction = Relationship.Direction.OUTGOING)
    val skills: List<Skill> = Collections.emptyList()
)