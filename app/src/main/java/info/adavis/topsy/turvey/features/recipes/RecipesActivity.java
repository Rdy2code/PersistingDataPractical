package info.adavis.topsy.turvey.features.recipes;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import info.adavis.topsy.turvey.R;
import info.adavis.topsy.turvey.db.RecipesDataProvider;
import info.adavis.topsy.turvey.db.TopsyTurvyDataSource;
import info.adavis.topsy.turvey.models.Recipe;

public class RecipesActivity extends AppCompatActivity {
    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private RecipesAdapter adapter;
    //Need to open and close the database based on app lifecycle
    private TopsyTurvyDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        dataSource = new TopsyTurvyDataSource ();
        dataSource.open();
        setupRecyclerView();
    }

    @Override
    protected void onResume () {
        super.onResume();
        //Call in to the data provider to retrieve recipes, then add them to the database table
        for (Recipe recipe : RecipesDataProvider.recipesList) {
            dataSource.createRecipe(recipe);
        }

        //Get records from the Realm, then loop through the records
        List<Recipe> allRecipes = dataSource.getAllRecipes();
        for (Recipe recipe : allRecipes) {
            Log.i(TAG, "recipe: " + recipe);
        }
    }

    @Override
    protected void onPause () {
        super.onPause();
        dataSource.close();
    }

    private void setupRecyclerView () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);
        recipesRecyclerView.setHasFixedSize(true);
        adapter = new RecipesAdapter( this );
        recipesRecyclerView.setAdapter( adapter );
    }
}
