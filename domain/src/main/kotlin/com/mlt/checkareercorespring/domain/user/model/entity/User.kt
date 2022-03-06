package com.mlt.checkareercorespring.domain.user.model.entity

import com.mlt.checkareercorespring.domain.skill.model.entity.Skill
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.util.*

@Node("User")
class User(
    @Id
    val id: Long,
    val name: String,

    @Relationship(type = "LIKES", direction = Relationship.Direction.OUTGOING)
    var skills: List<Skill> = Collections.emptyList()
)