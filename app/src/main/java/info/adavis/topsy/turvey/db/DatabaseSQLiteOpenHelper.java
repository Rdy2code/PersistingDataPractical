package info.adavis.topsy.turvey.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "topsy_turvey.db";
    public static final int VERSION_NUMBER = 1;

    public DatabaseSQLiteOpenHelper(Context context) {
        //Don't need factory, so make it null. Also use custom constants in super call, since
        //this will never be modified by any outside caller
        //This means the constructor only needs to take a context as an input
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Use the SQLiteDatabase class to create the individual tables
        sqLiteDatabase.execSQL(RecipeContract.CREATE_RECIPE_ENTRY_TABLE);
        sqLiteDatabase.execSQL(RecipeContract.CREATE_RECIPE_STEP_ENTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Drop table and recreate on launch
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipeContract.RecipeStepEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipeContract.RecipeEntry.TABLE_NAME);
        //Recreate the table
        onCreate(sqLiteDatabase);

    }
}
