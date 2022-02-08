package com.mlt.checkareercorespring.user.domain

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("User")
class User(
    @Id
    val id: Long,
    val name: String
)