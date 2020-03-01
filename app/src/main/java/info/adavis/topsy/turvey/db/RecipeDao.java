package info.adavis.topsy.turvey.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import info.adavis.topsy.turvey.models.Recipe;

@Dao
public interface RecipeDao {

    //These two lines of code take care of all boilerplate prev needed for ContentValues
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    long createRecipe (Recipe recipe);

    @Query("SELECT * FROM recipe")
    List<Recipe> getAllRecipes();

}
