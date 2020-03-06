package info.adavis.topsy.turvey.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.adavis.topsy.turvey.R;
import info.adavis.topsy.turvey.models.Recipe;
import info.adavis.topsy.turvey.models.RecipeStep;
import io.realm.RealmList;

public class RecipesDataProvider
{
    public static List<Recipe> recipesList;

    static
    {
        recipesList = new ArrayList<>();

        addRecipe(new Recipe("Cake", "Wonderful cake", R.drawable.cake_1));

        addRecipe(new Recipe("Pie", "Delicious Pie", R.drawable.pie_1));

        addRecipe(new Recipe("Pound Cake", "Fluffy cake", R.drawable.cake_2),
                  new RecipeStep(1, "mix all the ingredients"),
                  new RecipeStep(2, "preheat the oven"),
                  new RecipeStep(3, "stir"),
                  new RecipeStep(4, "bake"));
    }

    private static void addRecipe(Recipe recipe, RecipeStep... steps) {

        if (steps.length > 0) {
            //Initialize an empty RealmList of type RecipeStep object and add this field to the Recipe object
            recipe.setRecipeSteps(new RealmList<RecipeStep>());
            //Populate the list with the array of RecipeSteps received as input
            recipe.getRecipeSteps().addAll(Arrays.asList(steps));
        }
        //Add the Recipe name, description, and steps, if any, to the ArrayList
        recipesList.add( recipe );
    }
}
