package nl.miwnn.ch16.tildereplace.recipes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Wouter Stegeman
 * Unit entity that keeps track of units like grams, mililiters, etc
 */

@Entity
public class Unit {

    @Id
    @GeneratedValue
    private Long unitId;

    private String unitName;

    private String abbreviation;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }
}
