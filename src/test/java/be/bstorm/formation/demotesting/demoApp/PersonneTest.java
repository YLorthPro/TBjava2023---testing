package be.bstorm.formation.demotesting.demoApp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PersonneTest {

    @Test
    public void test_fromBll(){

        PersonneEntity entity = mock(PersonneEntity.class);

        when(entity.getId()).thenReturn(1L);
        when(entity.getName()).thenReturn("Geoffrey");
        when(entity.getBirthdate()).thenReturn(LocalDate.now());
        when(entity.getHeight()).thenReturn(19);

        Personne resultat = Personne.fromBll(entity);

        assertNotNull(resultat);
        assertEquals(1L, resultat.id());
        assertEquals("Geoffrey", resultat.name());
        assertEquals(LocalDate.now(), resultat.birthdate());
        assertEquals(19, resultat.height());

        verify(entity, times(1)).getId();
        verify(entity, times(1)).getName();
        verify(entity, times(1)).getBirthdate();
        verify(entity, times(1)).getHeight();

    }
}
