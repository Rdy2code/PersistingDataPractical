package info.adavis.topsy.turvey.models;

public class RecipeStep
{
    //Cupboard is not an ORM solution, so we remove the ID fields, as the steps will not just
    //be stored in the recipe table as a JSON
    private int stepNumber;

    private String instruction;

    public RecipeStep (int stepNumber, String instruction)
    {
        this.stepNumber = stepNumber;
        this.instruction = instruction;
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
