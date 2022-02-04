package com.mlt.checkareercorespring.samplenode.application

import com.mlt.checkareercorespring.samplenode.domain.SampleNode
import com.mlt.checkareercorespring.samplenode.domain.SampleNodeRepository
import com.mlt.checkareercorespring.samplenode.dto.RelationsSaveRequest
import com.mlt.checkareercorespring.samplenode.dto.SampleNodeGetResponse
import com.mlt.checkareercorespring.samplenode.dto.SampleNodeSaveRequest
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@RequiredArgsConstructor
class SampleNodeService(
    private val sampleNodeRepository: SampleNodeRepository
) {

    @Transactional
    fun createNode(node: SampleNode) {
        sampleNodeRepository.save(node)
    }

    @Transactional(readOnly = true)
    fun getAllNodes(): List<SampleNode> {
        return sampleNodeRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun getNode(nodeId: Long): SampleNode {
        return sampleNodeRepository.findById(nodeId).orElseThrow {
            throw IllegalArgumentException("존재하지 않는 노드입니다. (nodeId: ${nodeId})")
        }
    }

    @Transactional
    fun deleteNode(nodeId: Long) {
        sampleNodeRepository.deleteById(nodeId)
    }

    @Transactional
    fun createRelations(startNodeId: Long, targetNodeIds: Set<Long>) {
        val startNode = getNode(startNodeId)
        startNode.endNodes = targetNodeIds.map { getNode(it) }.toSet()
        sampleNodeRepository.save(startNode)
    }
}