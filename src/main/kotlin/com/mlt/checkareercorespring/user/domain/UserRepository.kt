package com.mlt.checkareercorespring.user.domain

import com.mlt.checkareercorespring.common.Paging.SortOrder
import org.neo4j.driver.internal.value.MapValue
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query

interface UserRepository : Neo4jRepository<User, Long> {

    // TODO: 정렬 기준 파라미터로 넘기기, 쿼리 결과를 Map이 아닌 Object에 매핑하기(당장 기능에는 영향 없음)
    @Query(
        "CALL gds.nodeSimilarity.stream('userSkillGraph')\n" +
                "YIELD node1, node2, similarity\n" +
                "WHERE gds.util.asNode(node1).id = \$userId\n" +
                "RETURN { user: { id: gds.util.asNode(node2).id, name:gds.util.asNode(node2).name }, similarity: similarity }\n" +
                "ORDER BY similarity DESC LIMIT \$limit"
    )
    fun findUserSkillSimilarityBySkillGraphOrderBySimilarityDesc(
        userId: Long,
        sortOrder: SortOrder,
        limit: Int?
    ): List<MapValue>
}