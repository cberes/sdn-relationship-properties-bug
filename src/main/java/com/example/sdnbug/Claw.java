package com.example.sdnbug;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@Node
public class Claw extends BaseEntity<Claw> {
}
