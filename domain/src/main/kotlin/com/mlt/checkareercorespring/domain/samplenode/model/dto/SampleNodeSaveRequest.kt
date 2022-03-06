package com.mlt.checkareercorespring.domain.samplenode.model.dto

import com.mlt.checkareercorespring.domain.samplenode.model.entity.SampleNode

data class SampleNodeSaveRequest(
    val id: Long,
    val name: String
) {
    fun toSampleNode(): SampleNode {
        return SampleNode(id, name)
    }
}