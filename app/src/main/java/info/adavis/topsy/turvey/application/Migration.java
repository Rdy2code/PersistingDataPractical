package info.adavis.topsy.turvey.application;

import info.adavis.topsy.turvey.models.RecipeFields;
import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

class Migration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        //Get a reference to Realm Schema
        RealmSchema schema = realm.getSchema();
        if (oldVersion == 1) {
            RealmObjectSchema recipeSchema = schema.get("Recipe");
            //Add the new field to the schema
            recipeSchema.addField(RecipeFields.NUMBER_OF_STARS, Integer.class)
                    //Set all recipes to five stars
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set(RecipeFields.NUMBER_OF_STARS, 5);
                        }
                    });

        }

    }
}
