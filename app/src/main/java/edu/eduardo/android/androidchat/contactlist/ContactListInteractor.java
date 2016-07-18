package edu.eduardo.android.androidchat.contactlist;

/**
 * Created by DELL on 18/06/2016.
 */
public interface ContactListInteractor {
    void subscribe();
    void unSubscribe();
    void destroyListener();
    void removeContact(String email);

}
