package edu.eduardo.android.androidchat.login;

import edu.eduardo.android.androidchat.login.events.LoginEvent;

/**
 * Created by DELL on 16/06/2016.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);

}
