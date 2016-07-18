package edu.eduardo.android.androidchat.contactlist.ui.adapters;

import edu.eduardo.android.androidchat.entities.User;

/**
 * Created by DELL on 18/06/2016.
 */
public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
