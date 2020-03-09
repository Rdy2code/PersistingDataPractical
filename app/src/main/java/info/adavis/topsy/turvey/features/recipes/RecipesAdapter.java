package info.adavis.topsy.turvey.features.recipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import info.adavis.topsy.turvey.R;
import info.adavis.topsy.turvey.models.Recipe;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * The RealmRecyclerViewAdapter automatically handles updates to its data and calls
 * notifyDataSetChanged()
 * Realm will get a list of Recipe objects for us and we can access these with getData()
 */

public class RecipesAdapter extends RealmRecyclerViewAdapter<Recipe, RecipesAdapter.ViewHolder> {

    public RecipesAdapter(@Nullable OrderedRealmCollection<Recipe> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView recipeImage;
        TextView recipeName;
        TextView recipeDescription;

        public ViewHolder (View v)
        {
            super( v );

            recipeImage = (ImageView) v.findViewById(R.id.recipe_image);
            recipeName = (TextView) v.findViewById(R.id.recipe_name);
            recipeDescription = (TextView) v.findViewById(R.id.recipe_description);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position)
    {
        Recipe recipe = getData().get( position );  //Access underlying data stored in Realm using getData()

        holder.recipeName.setText(recipe.getName());
        holder.recipeDescription.setText(recipe.getDescription());

        Picasso.get()
               .load(recipe.getImageResourceId())
               .resize(340, 200)
               .centerCrop()
               .into(holder.recipeImage);
    }
}
