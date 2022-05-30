package com.example.enigmamachinechat;

// data class holding data for each recyclerView message
public class Message {
    private String message, sender, time;

    public Message(String message, String sender, String time) {
        this.message = message;
        this.sender = sender;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public String getTime() {
        return time;
    }
}
