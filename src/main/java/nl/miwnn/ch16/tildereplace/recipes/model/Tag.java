/**
 * @author Remi van Loenen
 * An entity that can be used to tag recipes with for example categories
 */

package nl.miwnn.ch16.tildereplace.recipes.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Long tagId;

    @Column(unique = true)
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private List<Recipe> recipes;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
