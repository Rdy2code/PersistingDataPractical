package info.adavis.topsy.turvey.features.recipes;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import info.adavis.topsy.turvey.R;
import info.adavis.topsy.turvey.db.RecipesDataProvider;
import info.adavis.topsy.turvey.db.TopsyTurvyDataSource;
import info.adavis.topsy.turvey.models.Recipe;

public class RecipesActivity extends AppCompatActivity
{
    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private RecipesAdapter adapter;
    //Need to open and close the database based on app lifecycle
    private TopsyTurvyDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        dataSource = new TopsyTurvyDataSource (this);

        setupRecyclerView();
    }

    @Override
    protected void onResume () {
        super.onResume();
        dataSource.open();

        //Add recipes to the database. For testing purposes, use a static list from the DataProvider
        //and a for loop to iterate the list. Can this logic be placed in the abstraction layer?
        for (Recipe recipe : RecipesDataProvider.recipesList) {
            dataSource.createRecipe(recipe);
        }

        //In order to update a recipe, we first need to get the object from the table
        //then update a field in the object, then place it back in the table
        List<Recipe> allRecipes = getRecipes();
        Recipe recipe = allRecipes.get(0);
        recipe.setDescription("Wonderful yellow cake!");
        dataSource.updateRecipe(recipe);
        getRecipes();
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = dataSource.getAllRecipes();
        adapter.setRecipes(recipes);
        return recipes;
    }

    @Override
    protected void onPause ()
    {
        super.onPause();
        dataSource.close();
    }

    private void setupRecyclerView ()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);

        adapter = new RecipesAdapter( this );
        recipesRecyclerView.setAdapter( adapter );
    }

}
