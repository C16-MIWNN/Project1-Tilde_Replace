package nl.miwnn.ch16.tildereplace.recipes.model;

import jakarta.persistence.*;

@Entity
public class Food {

    @Id
    @GeneratedValue
    private Long FoodId;

    private String FoodName;

    public Long getFoodId() {
        return FoodId;
    }

    public void setFoodId(Long foodId) {
        FoodId = foodId;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

}
