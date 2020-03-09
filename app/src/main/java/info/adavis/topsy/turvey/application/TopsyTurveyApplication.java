package info.adavis.topsy.turvey.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Use this class to configure our Realm instance. Only needed when the process first starts.
 */

public class TopsyTurveyApplication extends Application {

    private int SCHEMA_VERSION = 2;     //Change the version here after adding the new field

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        //Define custom configuration
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .schemaVersion(SCHEMA_VERSION)  //Supply Schema version number for future migrations
                .migration(new Migration())
                .name("topsy_turvey.realm")
                .build();

        Realm.setDefaultConfiguration(configuration);
    }
}
