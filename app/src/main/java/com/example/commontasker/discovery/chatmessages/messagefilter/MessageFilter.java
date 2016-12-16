package com.example.commontasker.discovery.chatmessages.messagefilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.Getter;


public class MessageFilter {

    //this list contains all the messages in lowercase to filter messages
    @Getter
    private List<String> lowerCaseBlackList;

    private static final MessageFilter instance = new MessageFilter();


    public static MessageFilter getInstance() {
        return instance;
    }

    private MessageFilter() {

        lowerCaseBlackList = new ArrayList<>();

    }
    public boolean isFiltered(String message) throws MessageException {
        if (message == null) {
            throw new MessageException(MessageException.Reason.NULLMESSAGE);
        }

        if (message.length() <= 1) {
            throw new MessageException(MessageException.Reason.MESSAGETOOSHORT);
        }

        String[] chunckList = message.toLowerCase(Locale.US).split(" ");

        for (int i = 0; i < chunckList.length; i++) {
            if (lowerCaseBlackList.contains(chunckList[i])) {
                throw new MessageException(MessageException.Reason.MESSAGEBLACKLISTED);
            }
        }
        return false;
    }

}
