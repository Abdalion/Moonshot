package a1.t1mo.mobjav.a816.myapplication;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by dh-mob-tt on 08/11/16.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);
    }
}