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

    public void createRecipeStep (RecipeStep recipeStep, long recipeId) {
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String selectQuery = "SELECT * FROM recipe";
//        Cursor cursor = database.rawQuery(selectQuery, null);
//
//        try {
//            while (cursor.moveToNext()) {
//                //Get column indices
//                int indexName = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_NAME);
//                int indexDescription = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION);
//                int indexImage = cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID);
//                int indexId = cursor.getColumnIndex(RecipeContract.RecipeEntry._ID);
//
//                //Get row values
//                String name = cursor.getString(indexName);
//                String description = cursor.getString(indexDescription);
//                int image = cursor.getInt(indexImage);
//                long _id = cursor.getLong(indexId);
//
//                Recipe recipe = new Recipe(name, description, image);
//                recipe.setId(_id);
//                recipes.add(recipe);
//            }
//        } finally {
//            if (cursor != null && !cursor.isClosed()) {
//                cursor.close();
//            }
//        }
        return recipes;
    }

    public void updateRecipe(Recipe recipe) {
//        ContentValues values = getContentValues(recipe);
//        String selection = RecipeContract.RecipeEntry._ID + " = ?";
//        String[] selectionArgs = {String.valueOf(recipe.getId())};
//        int count = database.update(RecipeContract.RecipeEntry.TABLE_NAME,
//                values,
//                selection,
//                selectionArgs);
//        Log.d(TAG, "number of records updated " + count);
    }

    private ContentValues getContentValues(Recipe recipe) {
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME, recipe.getName());
        values.put(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION, recipe.getDescription());
        values.put(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID, recipe.getImageResourceId());
        return values;
    }

    public void deleteRecipe(Recipe recipe) {

//        String selection = RecipeContract.RecipeEntry._ID + " = ?";
//        String[] selectionArgs = {String.valueOf(recipe.getId())};
//        int count = database.delete(RecipeContract.RecipeEntry.TABLE_NAME,
//                selection,
//                selectionArgs);
//        Log.d(TAG, "number of records deleted " + count);
    }

    public void deleteAllRecipes() {

//        int count = database.delete(RecipeContract.RecipeEntry.TABLE_NAME,
//                null,
//                null);
//        Log.d(TAG, "number of records deleted " + count);
    }
}
