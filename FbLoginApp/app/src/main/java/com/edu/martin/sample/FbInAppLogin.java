package com.edu.martin.sample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.edu.martin.sample.util.TypeFaceSingleTone;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class FbInAppLogin extends ActionBarActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_fb_in_app_login);

        LoginButton button_fb_login = (LoginButton) findViewById(R.id.button_fb_login);
        button_fb_login.setBackgroundResource(R.drawable.fb_login_button);
        button_fb_login.setTypeface(TypeFaceSingleTone.getSingleInstance().getCatchTypeFace(getApplicationContext(), getResources().getString(R.string.font_gotham_medium)));
        button_fb_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                StringBuilder loginResponse = new StringBuilder();
                loginResponse.append(loginResult.getAccessToken().getUserId());
                loginResponse.append(loginResult.getAccessToken().getToken());
                Toast.makeText(getApplicationContext(), loginResponse.toString() + "", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ShareActivity.class));
                finish();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "onCancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
