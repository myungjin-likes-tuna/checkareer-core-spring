package com.mlt.checkareercorespring.samplenode.domain

import org.springframework.data.neo4j.repository.Neo4jRepository

interface SampleNodeRepository : Neo4jRepository<SampleNode, Long>