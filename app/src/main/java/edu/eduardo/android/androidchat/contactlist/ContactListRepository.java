package edu.eduardo.android.androidchat.contactlist;

/**
 * Created by DELL on 18/06/2016.
 */
public interface ContactListRepository {
    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void destroyListener();
    void subscribeToContactListEvents();
    void unSubscribeToContactListEvents();
    void changeConnectionStatus(boolean online);
}
