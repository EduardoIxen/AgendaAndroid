package edu.eduardo.android.androidchat.contactlist;

import org.greenrobot.eventbus.Subscribe;

import edu.eduardo.android.androidchat.contactlist.events.ContactListEvent;
import edu.eduardo.android.androidchat.contactlist.ui.ContactListView;
import edu.eduardo.android.androidchat.entities.User;
import edu.eduardo.android.androidchat.lib.EventBus;
import edu.eduardo.android.androidchat.lib.GreenRobotEventBus;

/**
 * Created by DELL on 18/06/2016.
 */
public class ContactListPresenterImpl implements ContactListPresenter {
    EventBus eventBus;
    ContactListView view;
    ContactListInteractor listInteractor;
    ContactListSessionInteractor sessionInteractor;

    public ContactListPresenterImpl(ContactListView view){
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        this.listInteractor = new ContactListInteractorImpl();
        this.sessionInteractor = new ContactListSessionInteractorImpl();
    }
    @Override
    public void onPause() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unSubscribe();
    }

    @Override
    public void onResume() {
        sessionInteractor.changeConnectionStatus(User.ONLINE);
        listInteractor.subscribe();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        listInteractor.destroyListener();
        view = null;
    }

    @Override
    public void signOff() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unSubscribe();
        listInteractor.destroyListener();
        sessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return sessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        listInteractor.removeContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEventType()) {
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }
    }

    private void onContactAdded(User user) {
        if (view != null) {
            view.onContactAdded(user);
        }
    }

    private void onContactChanged(User user) {
        if (view != null) {
            view.onContactChanged(user);
        }
    }

    private void onContactRemoved(User user) {
        if (view != null) {
            view.onContactRemoved(user);
        }
    }
}
