package nl.miwnn.ch16.tildereplace.recipes.model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Food {

    @Id
    @GeneratedValue
    private Long foodId;

    @Column(unique=true)
    private String foodName;

    private double energy;
    private double protein;
    private double fat;
    private double carbohydrates;
    private double fiber;
    private double salt;

    @ManyToMany
    private Set<Allergy> allergies;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private Set<Ingredient> Ingredients;


    public Set<Ingredient> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        Ingredients = ingredients;
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
        if (protein < 0) {
            throw new IllegalArgumentException("protein cannot be negative");
        }

        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        if (fat < 0) {
            throw new IllegalArgumentException("fat cannot be negative");
        }

        this.fat = fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        if (carbohydrates < 0) {
            throw new IllegalArgumentException("carbohydrates cannot be negative");
        }

        this.carbohydrates = carbohydrates;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        if (fiber < 0) {
            throw new IllegalArgumentException("fiber cannot be negative");
        }

        this.fiber = fiber;
    }

    public double getSalt() {
        return salt;
    }

    public void setSalt(double salt) {
        if (salt < 0) {
            throw new IllegalArgumentException("salt cannot be negative");
        }

        this.salt = salt;
    }

    public Set<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<Allergy> allergies) {
        this.allergies = allergies;
    }

}
