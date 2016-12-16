package com.example.commontasker.discovery.chatmessages.messagefilter;

import lombok.Getter;


public class MessageException extends Exception {

    public Reason getReason() {
        return reason;
    }

    public static enum Reason {NULLMESSAGE, MESSAGETOOSHORT, MESSAGEBLACKLISTED};

    @Getter private Reason reason;

    public MessageException() {

        super();
    }

    /**
     * Constructor
     *
     * @param message String message
     * @param cause The throwable object
     */
    public MessageException(String message, Throwable cause) {

        super(message, cause);
    }

    /**
     * Constructor
     *
     * @param message String message
     */
    public MessageException(String message) {

        super(message);
    }


    public MessageException(Throwable cause) {
        super(cause);
    }


    public MessageException(Reason reason) {

        this.reason = reason;
    }
}