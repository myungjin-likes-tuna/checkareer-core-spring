package com.mlt.checkareercorespring.samplenode.application

import com.mlt.checkareercorespring.samplenode.domain.SampleNodeRepository
import com.mlt.checkareercorespring.samplenode.dto.SampleNodeGetResponse
import com.mlt.checkareercorespring.samplenode.dto.SampleNodeSaveRequest
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class SampleNodeService(
    private val sampleNodeRepository: SampleNodeRepository
) {

    @Transactional
    fun createNode(sampleNodeSaveRequest: SampleNodeSaveRequest) {
        sampleNodeRepository.save(sampleNodeSaveRequest.toSampleNode())
    }

    @Transactional(readOnly = true)
    fun getAllNodes(): List<SampleNodeGetResponse> {
        return SampleNodeGetResponse.listOf(sampleNodeRepository.findAll())
    }

    @Transactional(readOnly = true)
    fun getNode(nodeId: Long): SampleNodeGetResponse {
        val node = sampleNodeRepository.findById(nodeId).orElseThrow {
            throw IllegalArgumentException("존재하지 않는 노드입니다. (nodeId: ${nodeId})")
        }
        return SampleNodeGetResponse.of(node)
    }

    @Transactional
    fun deleteNode(nodeId: Long) {
        sampleNodeRepository.deleteById(nodeId)
    }
}