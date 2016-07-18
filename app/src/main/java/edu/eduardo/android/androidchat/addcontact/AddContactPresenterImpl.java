package edu.eduardo.android.androidchat.addcontact;

import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import edu.eduardo.android.androidchat.R;
import edu.eduardo.android.androidchat.addcontact.AddContactPresenter;
import edu.eduardo.android.androidchat.addcontact.events.AddContactEvent;
import edu.eduardo.android.androidchat.addcontact.ui.AddContactView;
import edu.eduardo.android.androidchat.lib.EventBus;
import edu.eduardo.android.androidchat.lib.GreenRobotEventBus;

/**
 * Created by DELL on 18/06/2016.
 */
public class AddContactPresenterImpl implements AddContactPresenter {
    private EventBus eventBus;
    private AddContactView view;
    private AddContactInteractor interactor;

    public AddContactPresenterImpl(AddContactView view)
    {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddContactInteractorImpl();
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy()
    {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if (view != null){
            view.hideInput();
            view.showProgress();
        }
        interactor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if (view != null){
            view.hideProgress();
            view.showInput();

            if (event.isError()){
                view.contactnotAdded();
                //Toast.makeText(getApplicationContext(),"Bienvenido ", Toast.LENGTH_LONG).show();
            }else {
                view.contactAdded();
            }
        }
    }
}
