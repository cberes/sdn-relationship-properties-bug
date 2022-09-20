package com.example.sdnbug;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ClawRepository extends Neo4jRepository<Claw, String> {
}
