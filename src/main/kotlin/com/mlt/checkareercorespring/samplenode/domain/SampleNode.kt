package com.mlt.checkareercorespring.samplenode.domain

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node("SampleNode")
class SampleNode(
    @Id
    val id: Long,
    val name: String
)