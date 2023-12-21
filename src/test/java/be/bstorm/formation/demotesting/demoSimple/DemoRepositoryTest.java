package be.bstorm.formation.demotesting.demoSimple;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DemoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DemoTestRepository testRepository;

    private DemoTest entity;

    @BeforeEach
    void setUp(){
        entity = new DemoTest();
        entity.setId(1L);
        entity.setPhrase("Coucou les javas");
    }

    @AfterEach
    void end(){
        testRepository.deleteAll();
        entity = null;
    }

    @Test
    void findById_when_exist(){
        //Arrange
        DemoTest savedEntity = entityManager.merge(entity);
        entityManager.flush();

        //Act
        Optional<DemoTest> foundEntity = testRepository.findById(savedEntity.getId());

        //Assert
        assertTrue(foundEntity.isPresent());
        assertEquals(savedEntity.getPhrase(),foundEntity.get().getPhrase());
    }

    @Test
    void findById_when_pas_present(){
        //Arrange

        //Act
        Optional<DemoTest> pasPresent = testRepository.findById(-1222L);

        //Assert
        assertFalse(pasPresent.isPresent());
    }
}
