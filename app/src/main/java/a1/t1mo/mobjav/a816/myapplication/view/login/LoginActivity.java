package a1.t1mo.mobjav.a816.myapplication.view.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.view.MainActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;


import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    CallbackManager mCallbackManager;
    TwitterAuthClient mTwitterAuthClient= new TwitterAuthClient();
    FirebaseAuth mAuth;
    /*private FirebaseAuth.AuthStateListener mAuthListener;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.login_activity);

        mAuth = FirebaseAuth.getInstance();

        Button facebookButton = (Button)findViewById(R.id.login_button_facebook);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends"));
            }
        });
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        AuthCredential credential = FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken());
                        loginToFirebase(credential);
                    }
                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginActivity.this, "Login cancelled", Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        Button twitterButton = (Button) findViewById(R.id.login_button_twitter);
        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTwitterAuthClient.authorize(LoginActivity.this, new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        AuthCredential credential = TwitterAuthProvider.getCredential(
                                result.data.getAuthToken().token,
                                result.data.getAuthToken().secret);
                        loginToFirebase(credential);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Toast.makeText(LoginActivity.this, "Login cancelled", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
        if (mCallbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }

    private void loginToFirebase(final AuthCredential credential) {
         mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                 LoginActivity.this.finish();
                 startActivity(new Intent(LoginActivity.this, MainActivity.class));
             }
         });
    }
}
