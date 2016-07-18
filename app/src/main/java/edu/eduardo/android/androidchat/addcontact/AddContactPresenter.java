package edu.eduardo.android.androidchat.addcontact;

import edu.eduardo.android.androidchat.addcontact.events.AddContactEvent;

/**
 * Created by DELL on 18/06/2016.
 */
public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
