package info.adavis.topsy.turvey.models;

public class RecipeStep {

    public RecipeStep() {
    }

    public RecipeStep (int stepNumber, String instruction) {
        this.stepNumber = stepNumber;
        this.instruction = instruction;
    }

    private String id;

    private long recipeId;

    private int stepNumber;

    private String instruction;

    public String getId ()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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

}
