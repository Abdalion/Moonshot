package a1.t1mo.mobjav.a816.myapplication.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Egon on 04/12/2016.
 */

public class UserActions {

    public static boolean isUserLogged() {
        return (FirebaseAuth.getInstance().getCurrentUser() != null);
    }

    public static FirebaseUser getCurrentUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return (user != null) ? user : null;
    }
}
