package edu.eduardo.android.androidchat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by DELL on 14/06/2016.
 */
public class AndroidChatApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
