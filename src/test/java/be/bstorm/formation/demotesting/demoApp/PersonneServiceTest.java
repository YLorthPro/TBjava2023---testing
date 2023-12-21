package be.bstorm.formation.demotesting.demoApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonneServiceTest {
    @Mock
    private PersonneRepository personneRepository;
    @InjectMocks
    private PersonneServiceImpl personneService;

    private Personne personne;

    private PersonneEntity entity;

    @BeforeEach
    void setUp(){
        personne = new Personne(1L,"Bibou",180, LocalDate.now());
        entity = new PersonneEntity(personne.id(), personne.name(), personne.birthdate(), personne.height());
    }

    @Test
    void getById(){
        // Arrange
        when(personneRepository.findById(anyLong())).thenReturn(Optional.of(entity));

        // Act
        Optional<PersonneEntity> searchEntity = personneService.getById(1L);

        // Assert
        assertTrue(searchEntity.isPresent());
        assertEquals(entity, searchEntity.get());

    }

    @Test
    void create_when_ok(){
        // Arrange
        when(personneRepository.save(any(PersonneEntity.class))).thenReturn(entity);

        // Act
        personneService.create(personne);

        // Assert
        verify(personneRepository, times(1)).save(any(PersonneEntity.class));
    }

    @Test
    void create_when_null(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->personneService.create(null));

        String expectedMessage = "Pas cool";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}
