package com.example.sdnbug;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AddRelationshipAfterSaveTest {
    private final CatRepository catRepository;
    private final ClawRepository clawRepository;
    private final DogRepository dogRepository;

    @Autowired
    public AddRelationshipAfterSaveTest(CatRepository catRepository,
                                        ClawRepository clawRepository,
                                        DogRepository dogRepository) {
        this.catRepository = catRepository;
        this.clawRepository = clawRepository;
        this.dogRepository = dogRepository;
    }

    @BeforeEach
    void setup() {
        catRepository.deleteAll();
        clawRepository.deleteAll();
        dogRepository.deleteAll();
    }

    @Test
    void testCreateWithMultipleRelationshipProperties() {
        Cat cat = new Cat();
        cat.setName("Boots");
        cat.getClaws().add(new HasClaw(false, new Claw()));
        cat.getClaws().add(new HasClaw(true, new Claw()));
        catRepository.save(cat);

        Cat found1 = catRepository.findById(cat.getId()).get();
        assertThat(found1.getClaws()).hasSize(2); // passes
    }

    @Test
    void testUpdateWithNewRelationshipProperties() {
        Cat cat = new Cat();
        cat.setName("Boots");
        cat.getClaws().add(new HasClaw(false, new Claw()));
        catRepository.save(cat);

        Cat found1 = catRepository.findById(cat.getId()).get();
        assertThat(found1.getClaws()).hasSize(1);

        cat.getClaws().add(new HasClaw(true, new Claw()));
        catRepository.save(cat);

        Cat found2 = catRepository.findById(cat.getId()).get();
        assertThat(found2.getClaws()).hasSize(2); // fails: size is 1
    }

    @Test
    void testUpdateWithNormalRelationship() {
        Dog dog = new Dog();
        dog.setName("Rex");
        dog.getClaws().add(new Claw());
        dogRepository.save(dog);

        Dog found1 = dogRepository.findById(dog.getId()).get();
        assertThat(found1.getClaws()).hasSize(1);

        dog.getClaws().add(new Claw());
        dogRepository.save(dog);

        Dog found2 = dogRepository.findById(dog.getId()).get();
        assertThat(found2.getClaws()).hasSize(2); // passes
    }
}
