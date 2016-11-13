package a1.t1mo.mobjav.a816.myapplication.view.login.facebook;

import android.net.sip.SipAudioCall;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.like.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import a1.t1mo.mobjav.a816.myapplication.utils.Listener;

/**
 * Created by Egon on 13/11/2016.
 */

public class FacebookUtils {

    public static void requestUserInfo(final String field, final Listener<String> listener){
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            String respuesta = object.getString(field);
                            listener.done(respuesta);
                        }
                        catch(JSONException e) {
                            e.printStackTrace();
                        }

                        // Application code
                    }
                });
        Bundle parameters = new Bundle();

        //Agrego "fields" para indicarle que quiero los campos "id", "name" y "link"
        parameters.putString("field", field);

        request.setParameters(parameters);

        request.executeAsync();
    }

    public static boolean checkIfLogged() {
        AccessToken a = AccessToken.getCurrentAccessToken();
        return a != null;
    }
}
