package edu.eduardo.android.androidchat.lib;

/**
 * Created by DELL on 16/06/2016.
 */
public interface EventBus {
    void register(Object suscriber);
    void unregister(Object suscriber);
    void post(Object event);
}
