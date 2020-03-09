package info.adavis.topsy.turvey.models;

import java.util.List;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Recipe extends RealmObject {

    //region Constructors
    public Recipe() {
    }

    public Recipe (String name, String description, int imageResourceId)
    {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }
    //endregion

    //region Instance Variables
    @PrimaryKey         //Realm annotation
    private String id = UUID.randomUUID().toString();

    private String name;

    private String description;

    private int imageResourceId;

    //region Relationship between Recipe and RecipeStep classes
    //Connect with RecipeStep model by way of RealmList and include getter and setters
    private RealmList<RecipeStep> recipeSteps;
    //endregion

    private Integer numberOfStars; //Add a new field here. Once we add a new field we must add a migration class

    public RealmList<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(RealmList<RecipeStep> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }
    //endregion

    public String getId () {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getImageResourceId()
    {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId)
    {
        this.imageResourceId = imageResourceId;
    }
}
