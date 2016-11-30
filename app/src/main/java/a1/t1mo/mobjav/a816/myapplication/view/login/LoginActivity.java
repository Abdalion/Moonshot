package a1.t1mo.mobjav.a816.myapplication.view.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.User;
import a1.t1mo.mobjav.a816.myapplication.model.pelicula.Pelicula;
import a1.t1mo.mobjav.a816.myapplication.model.serie.Serie;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;


import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private CallbackManager mCallbackManager;
    private TwitterAuthClient mTwitterAuthClient= new TwitterAuthClient();
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference usersReference = firebaseDatabase.getReference("users");

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
                 user = FirebaseAuth.getInstance().getCurrentUser();

                 firebaseDatabase.getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot snapshot) {
                         if (snapshot.hasChild(user.getUid())) {
                             //TODO: llamar al dao de series y peliculas y settear los favoritos. TAmbien en el controller de series y pelicuals tiene que guardar en firebase
                             Log.d("Firebase", "Existe el user en la base de datos remota");
                         }
                         else {
                             Log.d("Firebase", "Creado user en la base de datos remota");
                             User myUser = new User(user.getDisplayName(), user.getUid(), new ArrayList<Serie>(), new ArrayList<Pelicula>());
                             firebaseDatabase.getReference().child("users").child(user.getUid()).setValue(myUser);
                         }
                     }
                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });


                 startActivity(new Intent(LoginActivity.this, MainActivity.class));
                 LoginActivity.this.finish();

             }
         });


    }

}
