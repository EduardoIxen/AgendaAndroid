package edu.eduardo.android.androidchat.contactlist.ui;

import edu.eduardo.android.androidchat.entities.User;

/**
 * Created by DELL on 17/06/2016.
 */
public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);

}
