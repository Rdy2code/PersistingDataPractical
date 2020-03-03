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

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class TopsyTurvyDataSource {

    //region Constants
    public static final String TAG  = TopsyTurvyDataSource.class.getSimpleName();
    //endregion

    //region Class handles
    private SQLiteDatabase database;            //Initialized in open() method
    private DatabaseSQLiteOpenHelper dbHelper;  //Initialized in constructor
    //endregion

    //region Constructor
    public TopsyTurvyDataSource(Context context) {

        this.dbHelper = new DatabaseSQLiteOpenHelper(context);
    }
    //endregion

    //Call this method in controlling activity class in onResume
    public void open () {
        this.database = dbHelper.getWritableDatabase();
        Log.d(TAG, "database is opened");
    }

    //Call this method in controlling activity class in onPause
    public void close () {
        dbHelper.close();
        Log.d(TAG, "database is closed");
    }

    //region Cupboard methods
    public void createRecipe (Recipe recipe) {
        //Add a record to the database with Cupboard
        long rowId = cupboard().withDatabase(database).put(recipe);
        Log.d(TAG, "recipe with id: " + rowId);
    }

    public List<Recipe> getAllRecipes() {
        //Get Cupboard instance and call the query method on the SQLite database recipe table
        return cupboard().withDatabase(database)
                .query(Recipe.class)
                .list();
    }

    //Replace a record with an updated Recipe object
    public void updateRecipe (Recipe recipe) {
        cupboard().withDatabase(database)
                .put(recipe);
    }

    public void deleteRecipe (Recipe recipe) {
        cupboard().withDatabase(database)
                .delete(recipe);
    }

    public void deleteAllRecipes () {
        cupboard().withDatabase(database)
                .delete(Recipe.class, null);
    }

    //endregion
}
