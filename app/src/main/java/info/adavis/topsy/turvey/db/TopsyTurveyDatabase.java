package info.adavis.topsy.turvey.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeStep;

/**
 * Container class. Room compiler will generate concrete implementation of this class for us
 * This class will instantiate a database. Takes care of writing all of the SQLite code for us.
 * This means we can remove the RecipeContract class and OpenHelper classes.
 */


@Database(entities = {Recipe.class, RecipeStep.class}, version = 2) //Change the version number too
public abstract class TopsyTurveyDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "topsy_turvey";
    //Singleton method
    private static TopsyTurveyDatabase INSTANCE = null;

    //Provide a database migration method for adding a new field to the Recipe table
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE recipe ADD COLUMN number_of_stars INTEGER");
        }
    };

    //Singleton pattern for creating a database instance
    public static TopsyTurveyDatabase getInstance(Context context) {
        //Check if instance is null. If so, wrap instantiation inside a synchronization block
        if (INSTANCE == null) {
            synchronized (TopsyTurveyDatabase.class) {
                //Room contains a database builder. We provide the required values.
                INSTANCE = Room.databaseBuilder(context,
                        TopsyTurveyDatabase.class,
                        DATABASE_NAME)
                        .addMigrations(MIGRATION_1_2)   //Let Room know how to handle dbase schema change
                        .build();
            }
        }
        return INSTANCE;
    }

    //These abstract methods are used to get a handle to the Dao and its methods
    public abstract RecipeDao recipeDao();
    public abstract RecipeStepDao recipeStepDao();

}
