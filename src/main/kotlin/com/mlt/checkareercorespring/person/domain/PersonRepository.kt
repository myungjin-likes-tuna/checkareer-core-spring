package com.mlt.checkareercorespring.person.domain

import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query

interface PersonRepository : Neo4jRepository<Person, Long> {

    @Query(
        "CALL gds.nodeSimilarity.stream('myGraph')\n" +
                "YIELD node1, node2, similarity\n" +
                "RETURN gds.util.asNode(node1).name AS Person1\n" +
                "ORDER BY similarity DESCENDING"
    )
    fun executeQueryExample(): List<Any>
}