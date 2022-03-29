package com.mlt.checkareercorespring.domain.user.repository

import com.mlt.checkareercorespring.domain.common.model.Paging
import com.mlt.checkareercorespring.domain.user.model.entity.User
import org.neo4j.driver.internal.value.MapValue
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query

interface UserRepository : Neo4jRepository<User, String> {

    @Query(
        "MATCH (u: User {id: \$userId})-[r:LIKES]->()\n" +
                "DELETE r"
    )
    fun deleteSkillRelationship(userId: String)

    // TODO: 정렬 기준 파라미터로 넘기기, 쿼리 결과를 Map이 아닌 Object에 매핑하기(당장 기능에는 영향 없음)
    @Query(
        "CALL gds.nodeSimilarity.stream('userSkillGraph')\n" +
                "YIELD node1, node2, similarity\n" +
                "WHERE gds.util.asNode(node1).id = \$userId\n" +
                "RETURN { user: { id: gds.util.asNode(node2).id, name:gds.util.asNode(node2).name }, skillSimilarity: similarity }\n" +
                "ORDER BY similarity DESC LIMIT \$limit"
    )
    fun findUserSkillSimilarityBySkillGraphOrderBySkillSimilarityDesc(
        userId: String,
        sortOrder: Paging.SortOrder,
        limit: Int?
    ): List<MapValue>

    @Query("CALL gds.graph.exists('userSkillGraph') YIELD exists")
    fun isExistUserSkillGraph(): Boolean

    @Query(
        "CALL gds.graph.create(\n" +
                "    'userSkillGraph',\n" +
                "    ['User', 'Skill'],\n" +
                "    {\n" +
                "        LIKES: {\n" +
                "            type: 'LIKES',\n" +
                "            properties: {\n" +
                "                strength: {\n" +
                "                    property: 'strength',\n" +
                "                    defaultValue: 1.0\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                ");"
    )
    fun createUserSkillGraph()

    @Query("CALL gds.graph.drop('userSkillGraph')")
    fun dropUserSkillGraph()
}