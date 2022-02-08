package com.mlt.checkareercorespring.user.domain

import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query

interface UserRepository : Neo4jRepository<User, Long> {

    @Query(
        "CALL gds.nodeSimilarity.stream('skillGraph')\n" +
                "YIELD node1, node2, similarity\n" +
                "RETURN gds.util.asNode(node1).name AS User\n" +
                "ORDER BY similarity DESCENDING"
    )
    fun findUserBySkillGraphOrderBySimilarity(): List<Any>
}