package com.example.sdnbug;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DogRepository extends Neo4jRepository<Dog, String> {
}
