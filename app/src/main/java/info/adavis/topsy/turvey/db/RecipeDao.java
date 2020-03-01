package info.adavis.topsy.turvey.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;
import io.reactivex.Flowable;

@Dao
public interface RecipeDao {

    //These two lines of code take care of all boilerplate prev needed for ContentValues
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    long createRecipe (Recipe recipe);

    //To listen for updates via RxJava, wrap return type in a Flowable
    @Query("SELECT * FROM recipe")
    Flowable<List<Recipe>> getAllRecipes(); //Wrap in Flowable to listen for updates

}
