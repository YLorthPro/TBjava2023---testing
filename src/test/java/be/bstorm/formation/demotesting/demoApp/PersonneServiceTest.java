package be.bstorm.formation.demotesting.demoApp;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    void getById_when_not_found() {
        when(personneRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<PersonneEntity> searchEntity = personneService.getById(1L);

        assertFalse(searchEntity.isPresent());
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

    @Test
    void getAll_WhenNoData_ReturnsEmptyList() {
        when(personneRepository.findAll()).thenReturn(Collections.emptyList());
        List<PersonneEntity> entities = personneService.getAll();
        assertTrue(entities.isEmpty());
    }

    @Test
    void getAll() {
        List<PersonneEntity> entities = Arrays.asList(entity, entity, entity);
        when(personneRepository.findAll()).thenReturn(entities);

        List<PersonneEntity> result = personneService.getAll();

        assertEquals(entities, result);
        verify(personneRepository, times(1)).findAll();
    }

    @Test
    void update_when_ok() {
        when(personneRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(personneRepository.save(any(PersonneEntity.class))).thenReturn(entity);

        personneService.update(personne, 1L);

        verify(personneRepository, times(1)).findById(1L);
        verify(personneRepository, times(1)).save(any(PersonneEntity.class));
    }

    @Test
    void update_WhenNullPassed_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            personneService.update(null, 1L);
        });

        String expectedMessage = "Pas cool";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update_WhenIdNotFound_ThrowsException() {
        when(personneRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            personneService.update(personne, 1L);
        });

        String expectedMessage = "Toujours pas cool";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete_when_ok() {
        when(personneRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(personneRepository).deleteById(anyLong());

        personneService.delete(1L);

        verify(personneRepository, times(1)).existsById(1L);
        verify(personneRepository, times(1)).deleteById(1L);
    }

}
