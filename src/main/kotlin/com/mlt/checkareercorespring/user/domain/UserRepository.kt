package com.mlt.checkareercorespring.user.domain

import org.neo4j.driver.internal.value.MapValue
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query

interface UserRepository : Neo4jRepository<User, Long> {

    @Query(
        "CALL gds.nodeSimilarity.stream('skillGraph')\n" +
                "YIELD node1, node2, similarity\n" +
                "RETURN gds.util.asNode(node1).name AS User\n" +
                "ORDER BY similarity DESCENDING"
    )
    fun findUserNameBySkillGraphOrderBySimilarity(): List<String>

    @Query(
        "CALL gds.nodeSimilarity.stream('skillGraph')\n" +
                "YIELD node1, node2, similarity\n" +
                "RETURN {user1: gds.util.asNode(node1).name, user2: gds.util.asNode(node2).name, similarity: similarity}\n" +
                "ORDER BY similarity DESCENDING"
    )
    fun findUserSkillSimilarityBySkillGraphOrderBySimilarityDesc(): List<MapValue>
}