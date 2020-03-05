package info.adavis.topsy.turvey.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Use this class to configure our Realm instance. Only needed when the process first starts.
 */

public class TopsyTurveyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        //Define custom configuration
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("topsy_turvey.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.deleteRealm(configuration);

        Realm.setDefaultConfiguration(configuration);
    }
}
