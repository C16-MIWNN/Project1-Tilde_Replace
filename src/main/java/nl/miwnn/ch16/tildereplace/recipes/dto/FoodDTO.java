package nl.miwnn.ch16.tildereplace.recipes.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class FoodDTO {

    private Long foodId;

    @NotBlank(groups = FoodInfo.class, message = "Ingredient naam mag niet leeg zijn")
    private String foodName;

    @Min(value = 0, groups = FoodInfo.class, message = "Energie moet positief zijn")
    private double energy;
    @Min(value = 0, groups = FoodInfo.class, message = "Eiwit moet positief zijn")
    private double protein;
    @Min(value = 0, groups = FoodInfo.class, message = "Vet moet positief zijn")
    private double fat;
    @Min(value = 0, groups = FoodInfo.class, message = "Koolhydraten moet positief zijn")
    private double carbohydrates;
    @Min(value = 0, groups = FoodInfo.class, message = "Vezels moet positief zijn")
    private double fiber;
    @Min(value = 0, groups = FoodInfo.class, message = "Zout moet positief zijn")
    private double salt;

    private Set<String> allergies = new HashSet<String>();;

    public FoodDTO() {
        allergies  = new HashSet<String>();
    }

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

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getSalt() {
        return salt;
    }

    public void setSalt(double salt) {
        this.salt = salt;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<String> allergies) {
        this.allergies = allergies;
    }

}
