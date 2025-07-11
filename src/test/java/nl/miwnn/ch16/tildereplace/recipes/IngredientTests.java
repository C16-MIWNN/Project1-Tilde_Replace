package nl.miwnn.ch16.tildereplace.recipes;


import nl.miwnn.ch16.tildereplace.recipes.model.Ingredient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTests {

    @Nested
    @DisplayName("When Ingredient exists")
    class whenIngredientExists {

        // Arrange
        private final Ingredient ingredient = new Ingredient();

        @Nested
        @DisplayName("Setting amount to negative value")
        class settingAmountToNegativeValue {

            @ParameterizedTest(name = " amount: {0}")
            @ValueSource(ints = {-1, -5, -100})
            @DisplayName("Throws IllegalArgumentException")
            void throwsIllegalArgumentException(int negativeValue) {

                // Act, Assert
                assertThrows(IllegalArgumentException.class,
                        () -> ingredient.setAmount(negativeValue));

            }

            @ParameterizedTest(name = " amount: {0}")
            @ValueSource(ints = {-1, -5, -100})
            @DisplayName("Throws IllegalArgumentException message")
            void setAmountTo(int negativeValue) {
                // Arrange
                String expectedMessage = "Ingredient amount must be 0 or above";

                // Act
                Exception exception = assertThrows(IllegalArgumentException.class,
                        () -> ingredient.setAmount(negativeValue));

                // Assert
                assertEquals(expectedMessage, exception.getMessage());
            }

        }

    }

}
