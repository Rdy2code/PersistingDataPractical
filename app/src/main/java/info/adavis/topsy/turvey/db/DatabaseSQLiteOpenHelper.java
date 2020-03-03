package info.adavis.topsy.turvey.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import info.adavis.topsy.turvey.models.Recipe;
import nl.littlerobots.cupboard.tools.gson.GsonListFieldConverterFactory;
import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;

import static info.adavis.topsy.turvey.db.TopsyTurvyDataSource.TAG;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * This class is used to create and update the SQLite Database and its tables
 */
public class DatabaseSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "topsy_turvey.db";
    public static final int VERSION_NUMBER = 1;

    public DatabaseSQLiteOpenHelper(Context context) {
        //Don't need factory, so make it null. Also use custom constants in super call, since
        //this will never be modified by any outside caller
        //This means the constructor only needs to take a context as an input
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    static {
        //Register the FieldConverterFactory so that we can convert the list of recipe steps to JSON
        CupboardFactory.setCupboard(
                new CupboardBuilder()
                .registerFieldConverterFactory(new GsonListFieldConverterFactory(new Gson()))
                .build());
        //Register the model class
        cupboard().register(Recipe.class);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        cupboard().withDatabase(sqLiteDatabase).createTables();
        Log.d(TAG, "onCreate: database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //For development purposes, drop and then create the table
        cupboard().withDatabase(sqLiteDatabase).dropAllTables();
        cupboard().withDatabase(sqLiteDatabase).createTables();
    }
}
