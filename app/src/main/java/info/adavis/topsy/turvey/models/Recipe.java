package info.adavis.topsy.turvey.models;

import java.util.List;

import nl.qbusict.cupboard.annotation.Ignore;

public class Recipe
{
    private Long _id;       //Cupboard expects _id

    private String name;

    private String description;

    private int imageResourceId;

    @Ignore
    private List<RecipeStep> steps;     //Cupboard does not support List fields

    public Recipe (String name, String description, int imageResourceId)
    {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public Long getId ()
    {
        return _id;
    }

    public void setId(Long id)
    {
        this._id = id;
    }

    public String getName()
    {
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

    public List<RecipeStep> getSteps()
    {
        return steps;
    }

    public void setSteps(List<RecipeStep> steps)
    {
        this.steps = steps;
    }
}
