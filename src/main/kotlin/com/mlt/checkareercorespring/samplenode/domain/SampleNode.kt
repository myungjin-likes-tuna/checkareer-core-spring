package com.mlt.checkareercorespring.samplenode.domain

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.util.*

@Node("SampleNode")
class SampleNode(
    @Id
    val id: Long,
    val name: String,
) {
    @Relationship(type = "relates", direction = Relationship.Direction.OUTGOING)
    var endNodes: Set<SampleNode> = Collections.emptySet()
}