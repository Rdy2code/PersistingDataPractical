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
import io.realm.Realm;

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

    public void createRecipe (final Recipe recipe) {

        //Insert records into database via Realm
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(recipe);
            }
        });
    }
}
