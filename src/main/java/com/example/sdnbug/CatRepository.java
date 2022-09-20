package com.example.sdnbug;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CatRepository extends Neo4jRepository<Cat, String> {
}
