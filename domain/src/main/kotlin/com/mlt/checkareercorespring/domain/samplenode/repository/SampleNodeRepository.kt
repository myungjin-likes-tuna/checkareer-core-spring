package com.mlt.checkareercorespring.domain.samplenode.repository

import com.mlt.checkareercorespring.domain.samplenode.model.entity.SampleNode
import org.springframework.data.neo4j.repository.Neo4jRepository

interface SampleNodeRepository : Neo4jRepository<SampleNode, Long>