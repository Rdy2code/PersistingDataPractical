package info.adavis.topsy.turvey.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

/**
 * This class is related to the Recipe model class. We specify primaryKeys so that we can use
 * multiple columns as primary keys. Setting up the @Entity properties as shown lets us eliminate the
 * id field from the RecipeStep class.
 */

@Entity (tableName = "recipe_steps",
        primaryKeys = {"step_number", "recipe_id"},
        indices = {@Index("recipe_id")},
        foreignKeys = @ForeignKey(entity =  Recipe.class,
        parentColumns = "id",   //This is the id of the Recipe class
        childColumns = "recipe_id"))    //This is the id of the RecipeStep class

public class RecipeStep
{
    @ColumnInfo (name = "recipe_id")
    private long recipeId;

    @ColumnInfo (name = "step_number")
    private int stepNumber;

    @ColumnInfo
    private String instruction;

    public RecipeStep (int stepNumber, String instruction)
    {
        this.stepNumber = stepNumber;
        this.instruction = instruction;
    }

    public long getRecipeId()
    {
        return recipeId;
    }

    public void setRecipeId(long recipeId)
    {
        this.recipeId = recipeId;
    }

    public int getStepNumber()
    {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber)
    {
        this.stepNumber = stepNumber;
    }

    public String getInstruction()
    {
        return instruction;
    }

    public void setInstruction(String instruction)
    {
        this.instruction = instruction;
    }

    @Override
    public String toString ()
    {
        return "RecipeStep{" +
                ", recipeId=" + recipeId +
                ", stepNumber=" + stepNumber +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}
