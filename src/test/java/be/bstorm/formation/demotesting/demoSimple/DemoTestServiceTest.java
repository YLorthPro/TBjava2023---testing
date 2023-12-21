package be.bstorm.formation.demotesting.demoSimple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
