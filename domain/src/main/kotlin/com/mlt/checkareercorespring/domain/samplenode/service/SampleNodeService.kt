package com.mlt.checkareercorespring.domain.samplenode.service

import com.mlt.checkareercorespring.domain.samplenode.model.entity.SampleNode
import com.mlt.checkareercorespring.domain.samplenode.repository.SampleNodeRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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