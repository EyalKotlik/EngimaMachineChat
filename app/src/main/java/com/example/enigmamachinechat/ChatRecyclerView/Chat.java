package com.example.enigmamachinechat.ChatRecyclerView;

import android.graphics.Bitmap;

import java.util.ArrayList;

// data class holding the data for each recyclerView chat
public class Chat {
    private String name, lastMessage, lastMessageTime;
    private ArrayList<String> chatMembers;
    private Bitmap icon;
    private int unreadMessagesCount;

    public Chat(String name, String lastMessage, String lastMessageTime, Bitmap icon, int unreadMessagesCount, ArrayList<String> chatMembers) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.icon = icon;
        this.unreadMessagesCount = unreadMessagesCount;
        this.chatMembers = chatMembers;
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

    public ArrayList<String> getChatMemebrs() {
        return chatMembers;
    }
}
