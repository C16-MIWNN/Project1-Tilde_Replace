package nl.miwnn.ch16.tildereplace.recipes.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/**
 * @author Wouter Stegeman
 * Allergy Entity
 */

@Entity
public class Allergy {

    @Id
    @GeneratedValue
    private Long allergyId;

    private String allergyName;


    @ManyToMany(fetch = FetchType.EAGER,
            mappedBy = "allergies",
            cascade = CascadeType.ALL)
    private Set<Food> foods;

    public Long getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(Long allergyId) {
        this.allergyId = allergyId;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }
}
