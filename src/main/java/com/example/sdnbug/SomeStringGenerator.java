package com.example.sdnbug;

import org.springframework.data.neo4j.core.schema.IdGenerator;

import java.util.UUID;

public class SomeStringGenerator implements IdGenerator<String> {

    @Override
    public String generateId(String primaryLabel, Object entity) {
        return primaryLabel + UUID.randomUUID();
    }
}
