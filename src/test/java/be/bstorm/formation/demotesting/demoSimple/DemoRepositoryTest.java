package be.bstorm.formation.demotesting.demoSimple;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) A mettre si on veut tester sur la vraie db
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

    @Test
    void findByPhrase_when_not_empty(){

        //Arrange
        DemoTest savedEntity = entityManager.merge(entity);
        entityManager.flush();

        //Act
        List<DemoTest> foundEntities = testRepository.findByPhrase(savedEntity.getPhrase());

        //Assert
        assertFalse(foundEntities.isEmpty());
        assertEquals(savedEntity.getPhrase(), foundEntities.get(0).getPhrase());
        assertEquals(1,foundEntities.size());
    }

    @Test
    void findByPhrase_when_empty(){

        //Arrange

        //Act
        List<DemoTest> foundEntities = testRepository.findByPhrase("coucou les javas");

        //Assert
        assertTrue(foundEntities.isEmpty());
    }
}
