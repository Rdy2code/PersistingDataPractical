package info.adavis.topsy.turvey.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeFields;
import info.adavis.topsy.turvey.models.RecipeStep;
import io.realm.Realm;
import io.realm.RealmResults;

public class TopsyTurvyDataSource {

    public static final String TAG  = TopsyTurvyDataSource.class.getSimpleName();

    Realm realm;

    //Call this method in controlling activity class in onResume
    public void open () {

        //Connect to Realm
        realm = Realm.getDefaultInstance();
        Log.d(TAG, "database is opened");
    }

    //Call this method in controlling activity class in onPause
    public void close () {

        //Close connection to Realm
        realm.close();
        Log.d(TAG, "database is closed");
    }

    //Insert a record
    public void createRecipe (final Recipe recipe) {
        //Insert records into database via Realm transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(recipe);
            }
        });
    }

    //Get (query) all records
    //For Realm queries you just need access to a defaultInstance of Realm
    public RealmResults<Recipe> getAllRecipes() {
        return realm.where(Recipe.class).findAll();
    }

    //Get recipes that have steps
    //Use Realm Field Names class
    public RealmResults<Recipe> getRecipesWithSteps() {
        return realm.where(Recipe.class).isNotEmpty(RecipeFields.RECIPE_STEPS.$).findAll();
    }

    //Get recipes with an "ie" in the name
    public RealmResults<Recipe> getRecipesWithIE() {
        return realm.where(Recipe.class).contains(RecipeFields.NAME, "ie").findAll();
    }

    public void modifyDescription () {
        //Get a managed Recipe instance
        final Recipe recipe = realm.where(Recipe.class).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                recipe.setDescription("Wonderful Yellow Cake!");
            }
        });

    }

    public void deleteRecipe (Recipe recipe) {
        final Recipe recipeManaged =
                realm.where(Recipe.class).equalTo(RecipeFields.ID, recipe.getId()).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                recipeManaged.deleteFromRealm();
            }
        });
    }

    public void deleteAllRecipes () {

        final RealmResults<Recipe> recipes = realm.where(Recipe.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                recipes.deleteAllFromRealm();
            }
        });
    }

}
