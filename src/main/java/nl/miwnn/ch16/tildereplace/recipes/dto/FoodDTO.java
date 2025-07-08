package nl.miwnn.ch16.tildereplace.recipes.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nl.miwnn.ch16.tildereplace.recipes.model.Allergy;

public class FoodDTO {

    private Long foodId;

    private String foodName;

    private double energy;
    private double protein;
    private double fat;
    private double carbohydrates;
    private double fiber;
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

    private String imageUrl;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fmanfaat.co.id%2Fwp-content%2Fuploads%2F2014%2F08%2Fwortel.jpg&f=1&nofb=1&ipt=4179d1a10a7e91b2521e02114102f24211a2dce62e7b32af037d6afd7ec7f0f7";
        //return this.imageUrl;
    }


}
