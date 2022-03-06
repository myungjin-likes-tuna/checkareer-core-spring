package com.mlt.checkareercorespring.controller

import com.mlt.checkareercorespring.domain.samplenode.model.dto.RelationsSaveRequest
import com.mlt.checkareercorespring.domain.samplenode.model.dto.SampleNodeGetResponse
import com.mlt.checkareercorespring.domain.samplenode.model.dto.SampleNodeSaveRequest
import com.mlt.checkareercorespring.domain.samplenode.service.SampleNodeService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
class SampleNodeController(
    private val sampleNodeService: SampleNodeService
) {

    @PostMapping("/nodes", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createNode(@RequestBody sampleNodeSaveRequest: SampleNodeSaveRequest): ResponseEntity<Void> {
        sampleNodeService.createNode(sampleNodeSaveRequest.toSampleNode())
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/nodes", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllNodes(): ResponseEntity<List<SampleNodeGetResponse>> {
        return ResponseEntity.ok(SampleNodeGetResponse.listOf(sampleNodeService.getAllNodes()))
    }

    @GetMapping("/nodes/{nodeId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getNode(@PathVariable nodeId: Long): ResponseEntity<SampleNodeGetResponse> {
        return ResponseEntity.ok(SampleNodeGetResponse.of(sampleNodeService.getNode(nodeId)))
    }

    @DeleteMapping("/nodes/{nodeId}")
    fun deleteNode(@PathVariable nodeId: Long): ResponseEntity<Void> {
        sampleNodeService.deleteNode(nodeId)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/nodes/relations/{startNodeId}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createRelations(
        @PathVariable startNodeId: Long,
        @RequestBody relationSaveRequest: RelationsSaveRequest
    ): ResponseEntity<Void> {
        sampleNodeService.createRelations(startNodeId, relationSaveRequest.targetNodeIds)
        return ResponseEntity(HttpStatus.OK)
    }
}