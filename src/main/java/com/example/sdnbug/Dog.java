package com.example.sdnbug;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Node
public class Dog extends BaseEntity<Dog> {
    private String name;

    @Relationship("HAS_CLAW")
    private List<Claw> claws = new ArrayList<>();
}
