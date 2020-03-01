package info.adavis.topsy.turvey.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeStep;

public class TopsyTurvyDataSource {

    public static final String TAG  = TopsyTurvyDataSource.class.getSimpleName();
    private final RecipeDao recipeDao;
    private final RecipeStepDao recipeStepDao;

    public TopsyTurvyDataSource(Context context) {

        TopsyTurveyDatabase database = TopsyTurveyDatabase.getInstance(context);
        recipeDao = database.recipeDao();
        recipeStepDao = database.recipeStepDao();
    }

    public void createRecipe (Recipe recipe) {
        long rowId = recipeDao.createRecipe(recipe);    //This writes all sql for us
        Log.d(TAG, "recipe with id: " + rowId);
        //Get the steps for a given recipe and add them to their own table
        List<RecipeStep> steps = recipe.getSteps();
        if (steps != null && steps.size() > 0) {
            for (RecipeStep step : steps) {
                step.setRecipeId(rowId);
            }
            recipeStepDao.insertAll(steps);
        }
        Log.d(TAG, "createRecipe: the id: " + rowId);
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = recipeDao.getAllRecipes();
        for (Recipe recipe : recipes) {
            List<RecipeStep> steps = recipeStepDao.getAllRecipeStepsByRecipeId(recipe.getId());
            recipe.setSteps(steps);
        }
        return recipes;
    }
}
