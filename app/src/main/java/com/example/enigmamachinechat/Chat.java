package com.example.enigmamachinechat;

import android.graphics.Bitmap;

public class Chat {
    private String name, lastMessage, lastMessageTime;
    private Bitmap icon;
    private int unreadMessagesCount;

    public Chat(String name, String lastMessage, String lastMessageTime, Bitmap icon, int unreadMessagesCount) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.icon = icon;
        this.unreadMessagesCount = unreadMessagesCount;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public int getUnreadMessagesCount() {
        return unreadMessagesCount;
    }
}
