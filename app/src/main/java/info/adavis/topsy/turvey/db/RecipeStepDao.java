package info.adavis.topsy.turvey.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import info.adavis.topsy.turvey.models.RecipeStep;

@Dao
public interface RecipeStepDao {

    @Insert
    void insertAll(List<RecipeStep> steps);

    @Query("SELECT * FROM recipe_steps WHERE recipe_id = :recipeId")
    List<RecipeStep> getAllRecipeStepsByRecipeId(long recipeId);
}
