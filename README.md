# sdn-relationship-properties-bug

[SDN](https://github.com/spring-projects/spring-data-neo4j) will not save relationships annotated with
`@RelationshipProperties` if the start node already exists.

For example, say you have a schema like this, where two nodes are related and this is represented by an
object annotated with `@RelationshipProperties`

    @Node
    public class Cat {
        @Relationship("HAS_CLAW") List<HasClaw> claws;
    }

    @Node
    public class Claw {
    }

    @RelationshipProperties
    public class HasClaw {
        @Id @GeneratedValue Long id;

        boolean dewclaw;
    
        @TargetNode Claw claw;
    }

Once you create a `Cat`, you can no longer add new `HAS_CLAWS` relationships

    Cat cat = new Cat();
    cat.getClaws().add(new HasClaw(false, new Claw()));
    catRepository.save(cat);

    cat.getClaws().add(new HasClaw(true, new Claw()));
    catRepository.save(cat);

    Cat found = catRepository.findById(cat.getId()).get();
    assertThat(found.getClaws()).hasSize(2); // fails: size is 1

Note that relationships not annotated with `@RelationshipProperties` (for example the `Dog` node in the example code)
are not affected by this.

This example worked correctly in SDN 6.3.2, but it fails with SDN 6.3.3.

## Requirements

Don't forget to set database credentials in [application.properties](src/main/resources/application.properties).

## Tests

Run [tests](src/test/java/com/example/sdnbug/AddRelationshipAfterSaveTest.java) via `mvn clean test` or via an IDE.

