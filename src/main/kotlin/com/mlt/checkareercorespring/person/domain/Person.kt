package com.mlt.checkareercorespring.person.domain

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("Person")
class Person(
    @Id
    val id: Long,
    val name: String
)