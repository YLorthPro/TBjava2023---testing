package be.bstorm.formation.demotesting.demoSimple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DemoTestServiceTest {

    DemoTestService demoTestService = new DemoTestService();

    @Test
    public void testConcatenation() {

        // trois "A" -> Arrange, Act, Assert
        // Arrange -> Préparation du test: Instancier les objets, les différentes variables,...
        // Act -> Appel de la méthode à tester
        // Assert -> Vérification du résultat de la méthode

        // Arrange
        String motA = "Hello";
        String motB = "World";

        //Act
        String result = demoTestService.concatenation("Hello", "World");

        //Assert
        assertEquals("Hello World", result);
    }

    //diviser(a,b) -> Cas 1 tout ok, Cas 2 diviser par 0
    @Test
    public void test_diviser_quand_tout_ok(){
        // Arrange
        int a = 10;
        int b = 5;

        //Act
        int result = demoTestService.diviser(a,b);

        //Assert
        assertEquals(2, result);
    }

    @Test
    public void test_diviser_par_zero(){
        // Arange
        int a = 10;
        int b = 0;

        // Act & Assert
        assertThrows(ArithmeticException.class, ()->demoTestService.diviser(a,b));
    }

    @Test
    public void test_addition(){
        //Arange
        int a = 10;
        int b = 5;

        //Act
        int resultat = demoTestService.addition(a,b);

        //Assert
        assertEquals(15,resultat);
    }

    @Test
    public void test_split_ok(){
        //Arange
        String phrase = "Hello World";

        //Act
        List<String> resultat = demoTestService.split(phrase);

        //Assert
        assertEquals(Arrays.asList("Hello","World"), resultat);
    }

    @Test
    public void test_split_notok(){
        //Arange
        String phrase = null;

        //Act & Assert
        assertThrows(NullPointerException.class,()->demoTestService.split(phrase));
    }

    @Test
    public void test_lowercase_ok(){
        //Arange
        String phrase = "test";

        //Act
        boolean resultat = demoTestService.isLowercase(phrase);

        //Assert
        assertTrue(resultat);
    }

    @Test
    public void test_lowercase_ko(){
        //Arange
        String phrase = "Test";

        //Act
        boolean resultat = demoTestService.isLowercase(phrase);

        //Assert
        assertFalse(resultat);
    }

    @Test
    public void test_lowercase_quand_null(){
        //Arange
        String phrase = null;

        //Act & Assert
        assertThrows(NullPointerException.class, ()->demoTestService.isLowercase(phrase));
    }

}
