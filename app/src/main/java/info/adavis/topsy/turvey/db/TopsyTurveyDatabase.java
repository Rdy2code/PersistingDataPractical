package info.adavis.topsy.turvey.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeStep;

/**
 * Container class. Room compiler will generate concrete implementation of this class for us
 * This class will instantiate a database. Takes care of writing all of the SQLite code for us.
 * This means we can remove the RecipeContract class and OpenHelper classes.
 */


@Database(entities = {Recipe.class, RecipeStep.class}, version = 1)
public abstract class TopsyTurveyDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "topsy_turvey";
    //Singleton method
    private static TopsyTurveyDatabase INSTANCE = null;

    public static TopsyTurveyDatabase getInstance(Context context) {
        //Check if instance is null. If so, wrap instantiation inside a synchronization block
        if (INSTANCE == null) {
            synchronized (TopsyTurveyDatabase.class) {
                //Room contains a database builder. We provide the required values.
                INSTANCE = Room.databaseBuilder(context,
                        TopsyTurveyDatabase.class,
                        DATABASE_NAME).build();
            }
        }
        return INSTANCE;
    }

    //These abstract methods are used to get a handle to the Dao and its methods
    public abstract RecipeDao recipeDao();
    public abstract RecipeStepDao recipeStepDao();

}
