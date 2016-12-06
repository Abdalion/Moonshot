package a1.t1mo.mobjav.a816.myapplication;

import android.app.Application;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;

/**
 * Created by dh-mob-tt on 08/11/16.
 */

public class App extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "lpjIdmkqwOTPrQ48k9fa5rR2X";
    private static final String TWITTER_SECRET = "5DKte2Gp5upSDtK8JkRoUmE51Jf9e30UjO0hpoRAS1vqYNWIOj";


    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);
    }
}