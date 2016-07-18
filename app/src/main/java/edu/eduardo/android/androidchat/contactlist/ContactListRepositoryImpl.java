package edu.eduardo.android.androidchat.contactlist;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import edu.eduardo.android.androidchat.contactlist.events.ContactListEvent;
import edu.eduardo.android.androidchat.domain.FirebaseHelper;
import edu.eduardo.android.androidchat.entities.User;
import edu.eduardo.android.androidchat.lib.EventBus;
import edu.eduardo.android.androidchat.lib.GreenRobotEventBus;

/**
 * Created by DELL on 18/06/2016.
 */
public class ContactListRepositoryImpl implements ContactListRepository {
    private EventBus eventbus;
    private FirebaseHelper helper;
    private ChildEventListener contactEventListener;

    public ContactListRepositoryImpl(){
        this.helper = FirebaseHelper.getInstance();
        this.eventbus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void signOff() {
        helper.signOff();
    }

    @Override
    public String getCurrentUserEmail() {

        return helper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentuserEmail = helper.getAuthUserEmail();
        helper.getOnContactReference(currentuserEmail, email).removeValue();
        helper.getOnContactReference(email, currentuserEmail).removeValue();
    }

    @Override
    public void destroyListener() {
        contactEventListener = null;
    }

    @Override
    public void subscribeToContactListEvents() {
        if (contactEventListener == null) {
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    handleContact(dataSnapshot, ContactListEvent.onContactAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContactChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, ContactListEvent.onContactRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(FirebaseError firebaseError) {}
             };
        }
        helper.getMyContactsReference().addChildEventListener(contactEventListener);
    }

    private void handleContact(DataSnapshot dataSnapshot, int type) {
        String email = dataSnapshot.getKey();
        email = email.replace("_",".");
        boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
        User user = new User();
        user.setEmail(email);
        user.setOnline(online);
        post(type, user);
    }

    private void post(int type, User user) {
        ContactListEvent event = new ContactListEvent();
        event.setEventType(type);
        event.setUser(user);
        eventbus.post(event);
    }

    @Override
    public void unSubscribeToContactListEvents() {
        if (contactEventListener != null){
            helper.getMyContactsReference().removeEventListener(contactEventListener);
        }

    }

    @Override
    public void changeConnectionStatus(boolean online) {

    }
}
