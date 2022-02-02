package com.mlt.checkareercorespring.samplenode.dto

import com.mlt.checkareercorespring.samplenode.domain.SampleNode

data class SampleNodeSaveRequest(
    val id: Long,
    val name: String
) {
    fun toSampleNode(): SampleNode {
        return SampleNode(id, name)
    }
}