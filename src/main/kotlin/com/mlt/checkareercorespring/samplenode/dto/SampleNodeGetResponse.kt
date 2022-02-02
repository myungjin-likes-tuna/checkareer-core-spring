package com.mlt.checkareercorespring.samplenode.dto

import com.mlt.checkareercorespring.samplenode.domain.SampleNode

data class SampleNodeGetResponse(
    val id: Long,
    val name: String
) {
    companion object {
        fun listOf(sampleNodes: List<SampleNode>): List<SampleNodeGetResponse> {
            return sampleNodes.map { of(it) }
        }

        fun of(sampleNode: SampleNode): SampleNodeGetResponse {
            return SampleNodeGetResponse(sampleNode.id, sampleNode.name)
        }
    }
}