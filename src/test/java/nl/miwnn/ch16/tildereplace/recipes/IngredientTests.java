package nl.miwnn.ch16.tildereplace.recipes;


import nl.miwnn.ch16.tildereplace.recipes.model.Food;
import nl.miwnn.ch16.tildereplace.recipes.model.Ingredient;
import nl.miwnn.ch16.tildereplace.recipes.model.Unit;
import org.hibernate.jdbc.Expectation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTests {

    @Test
    @DisplayName("Setting negative ingredient amount throws IllegalArgumentException")
    void setNegativeIngredientAmountThrowsException() {
        // Arrange
        Ingredient ingredient = new Ingredient();

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ingredient.setAmount(-5));

        // Assert
        String expectedMessage = "Ingredient amount must be 0 or above";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
