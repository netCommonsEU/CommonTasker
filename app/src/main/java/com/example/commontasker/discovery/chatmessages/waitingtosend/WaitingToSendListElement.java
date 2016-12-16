package com.example.commontasker.discovery.chatmessages.waitingtosend;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

class WaitingToSendListElement {

    @Getter private final List<String> waitingToSendList;

    public WaitingToSendListElement () {

        waitingToSendList = new ArrayList<>();
    }

    public List<String> getWaitingToSendList() {
        return waitingToSendList;
    }
}
