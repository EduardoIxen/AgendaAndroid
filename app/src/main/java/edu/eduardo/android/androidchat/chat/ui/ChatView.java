package edu.eduardo.android.androidchat.chat.ui;

import edu.eduardo.android.androidchat.chat.entities.ChatMessage;

/**
 * Created by ykro.
 */
public interface ChatView {
    void sendMessage();
    void onMessageReceived(ChatMessage msg);
}
