package edu.eduardo.android.androidchat.chat.events;

import edu.eduardo.android.androidchat.chat.entities.ChatMessage;

/**
 * Created by ykro.
 */
public class ChatEvent {
    ChatMessage msg;

    public ChatEvent(ChatMessage msg) {
        this.msg = msg;
    }

    public ChatMessage getMessage() {
        return msg;
    }
}
