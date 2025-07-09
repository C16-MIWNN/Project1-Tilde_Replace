package nl.miwnn.ch16.tildereplace.recipes.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nl.miwnn.ch16.tildereplace.recipes.model.Food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

/**
 * @author Wouter Stegeman
 * Testing class for Food objects
 */
class FoodTests {

    @Test
    void setNegativeValues() {
        Food food = new Food();
        double negativeValue = -1;

        Assertions.assertAll(
                (() -> assertThrows(IllegalArgumentException.class, () ->
                        {
                            food.setEnergy(negativeValue);
                        }
                )),
                (() -> assertThrows(IllegalArgumentException.class, () ->
                    {
                        food.setProtein(negativeValue);
                    }
                 )),
                 (() -> assertThrows(IllegalArgumentException.class, () ->
                    {
                        food.setFat(negativeValue);
                    }
                 )),
                 (() -> assertThrows(IllegalArgumentException.class, () ->
                        {
                            food.setCarbohydrates(negativeValue);
                        }
                 )),
                 (() -> assertThrows(IllegalArgumentException.class, () ->
                        {
                            food.setFiber(negativeValue);
                        }
                 )),
                 (() -> assertThrows(IllegalArgumentException.class, () ->
                        {
                            food.setSalt(negativeValue);
                        }
                 ))
        );
    }


    @Test
    void setPositiveValues() {
        Food food = new Food();
        double positiveValue = 1;

        Assertions.assertAll(
                (() -> assertDoesNotThrow(() ->
                        {
                            food.setEnergy(positiveValue);
                        }
                )),
                (() -> assertDoesNotThrow(() ->
                    {
                        food.setProtein(positiveValue);
                    }
                 )),
                 (() -> assertDoesNotThrow(() ->
                    {
                        food.setFat(positiveValue);
                    }
                 )),
                 (() -> assertDoesNotThrow(() ->
                        {
                            food.setCarbohydrates(positiveValue);
                        }
                 )),
                 (() -> assertDoesNotThrow(() ->
                        {
                            food.setFiber(positiveValue);
                        }
                 )),
                 (() -> assertDoesNotThrow(() ->
                        {
                            food.setSalt(positiveValue);
                        }
                 ))
        );
    }


}
