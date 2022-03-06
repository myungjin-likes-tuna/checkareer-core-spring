package com.mlt.checkareercorespring.domain.samplenode.model.dto

import com.mlt.checkareercorespring.domain.samplenode.model.entity.SampleNode

data class SampleNodeGetResponse(
    val id: Long,
    val name: String,
    val endNodes: Set<SampleNode>
) {
    companion object {
        fun listOf(sampleNodes: List<SampleNode>): List<SampleNodeGetResponse> {
            return sampleNodes.map { of(it) }
        }

        fun of(sampleNode: SampleNode): SampleNodeGetResponse {
            return SampleNodeGetResponse(sampleNode.id, sampleNode.name, sampleNode.endNodes)
        }
    }
}