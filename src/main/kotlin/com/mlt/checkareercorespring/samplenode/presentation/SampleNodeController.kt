package com.mlt.checkareercorespring.samplenode.presentation

import com.mlt.checkareercorespring.samplenode.application.SampleNodeService
import com.mlt.checkareercorespring.samplenode.dto.SampleNodeGetResponse
import com.mlt.checkareercorespring.samplenode.dto.SampleNodeSaveRequest
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
        sampleNodeService.createNode(sampleNodeSaveRequest)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/nodes", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllNodes(): ResponseEntity<List<SampleNodeGetResponse>> {
        return ResponseEntity.ok(sampleNodeService.getAllNodes())
    }

    @GetMapping("/nodes/{nodeId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getNode(@PathVariable nodeId: Long): ResponseEntity<SampleNodeGetResponse> {
        return ResponseEntity.ok(sampleNodeService.getNode(nodeId))
    }

    @DeleteMapping("/nodes/{nodeId}")
    fun deleteNode(@PathVariable nodeId: Long): ResponseEntity<Void> {
        sampleNodeService.deleteNode(nodeId)
        return ResponseEntity(HttpStatus.OK)
    }
}