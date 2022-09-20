package com.example.sdnbug;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Getter
@Setter
@NoArgsConstructor
public class HasClaw {
    @Id
    @GeneratedValue
    private Long id;

    private boolean dewclaw;

    @TargetNode
    private Claw claw;

    public HasClaw(boolean dewclaw, Claw claw) {
        this.dewclaw = dewclaw;
        this.claw = claw;
    }
}
