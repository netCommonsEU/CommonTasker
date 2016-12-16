package com.example.commontasker;

/**
 * Created by Αρης on 3/10/2016.
 */
public class ChatMessage {
    public boolean left;
    public String message;
    public String phone;
    public ChatMessage(boolean left, String message,String phone) {
        super();
        this.left = left;
        this.message = message;
        this.phone = phone;
    }
}