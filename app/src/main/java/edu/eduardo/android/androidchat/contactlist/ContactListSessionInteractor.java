package edu.eduardo.android.androidchat.contactlist;

/**
 * Created by DELL on 18/06/2016.
 */
public interface ContactListSessionInteractor {
    void signOff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
