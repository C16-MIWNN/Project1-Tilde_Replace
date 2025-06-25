package nl.miwnn.ch16.tildereplace.recipes.model;

import jakarta.persistence.*;

@Entity
public class Food {

    @Id
    @GeneratedValue
    private Long foodId;

    private String foodName;

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

}
