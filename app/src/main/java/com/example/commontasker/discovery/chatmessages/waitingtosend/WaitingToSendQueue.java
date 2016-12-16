package com.example.commontasker.discovery.chatmessages.waitingtosend;

import java.util.ArrayList;
import java.util.List;


public class WaitingToSendQueue {

    private final List<WaitingToSendListElement> waitingToSend;

    private static final WaitingToSendQueue instance = new WaitingToSendQueue();


    public static WaitingToSendQueue getInstance() {
        return instance;
    }

    private WaitingToSendQueue() {
        waitingToSend = new ArrayList<>();
    }

    public List<String> getWaitingToSendItemsList(int tabNumber) {

        if ((tabNumber - 1) >= 0 && (tabNumber - 1) <= waitingToSend.size() - 1) {

            if(waitingToSend.get((tabNumber - 1)) == null) {
                waitingToSend.set((tabNumber - 1), new WaitingToSendListElement());
            }

        } else {

            waitingToSend.add((tabNumber - 1), new WaitingToSendListElement());
        }

        return waitingToSend.get((tabNumber - 1)).getWaitingToSendList();
    }
}
