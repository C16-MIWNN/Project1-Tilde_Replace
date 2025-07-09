package nl.miwnn.ch16.tildereplace.recipes.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nl.miwnn.ch16.tildereplace.recipes.model.Food;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Wouter Stegeman
 * Testing class for Food objects
 */
class FoodTests {

    @Test
    void setFoodEnergy() {
        // Arrange
        Food food = new Food();
        double badEnergyValue = 1;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                {
                    food.setEnergy(badEnergyValue);
                }
        );
    }


    @Test
    void setFoodProtein() {
        // Arrange
        Food food = new Food();
        double badProteinValue = 1;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                {
                    food.setProtein(badProteinValue);
                }
        );
    }

}
