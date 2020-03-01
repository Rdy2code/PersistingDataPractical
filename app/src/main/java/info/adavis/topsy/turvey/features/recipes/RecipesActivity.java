package info.adavis.topsy.turvey.features.recipes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.Callable;

import info.adavis.topsy.turvey.R;
import info.adavis.topsy.turvey.db.RecipesDataProvider;
import info.adavis.topsy.turvey.db.TopsyTurvyDataSource;
import info.adavis.topsy.turvey.models.Recipe;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;



public class RecipesActivity extends AppCompatActivity
{
    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private RecipesAdapter adapter;
    //Need to open and close the database based on app lifecycle
    private TopsyTurvyDataSource dataSource;
    private Disposable disposable;

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

        //Observe the call on the main thread using RxJava. Always store as disposable.
        disposable = dataSource.getAllRecipes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Recipe>>() {
                    @Override
                    public void accept(List<Recipe> recipes) throws Exception {
                        adapter.setRecipes(recipes);
                        adapter.notifyDataSetChanged();
                    }
                });

        //Instead of an AsyncTask, we use a Completable.fromCallable() to do the background work
        Completable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (Recipe recipe : RecipesDataProvider.recipesList) {
                    dataSource.createRecipe(recipe);
                }
                //Null for now, since not doing anything with the results for the moment
                return null;
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void setupRecyclerView () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);
        recipesRecyclerView.setHasFixedSize(true);
        adapter = new RecipesAdapter( this );
        recipesRecyclerView.setAdapter( adapter );
    }

    //Dispose of anything we subscribe to or observe
    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
