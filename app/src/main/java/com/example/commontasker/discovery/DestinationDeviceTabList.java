package com.example.commontasker.discovery;

import android.net.wifi.p2p.WifiP2pDevice;
import android.support.annotation.NonNull;

import com.example.commontasker.discovery.model.P2pDestinationDevice;
import com.example.commontasker.discovery.utilities.UseOnlyPrivateHere;

import java.util.ArrayList;
import java.util.List;


public class DestinationDeviceTabList {

    @UseOnlyPrivateHere
    private final List<P2pDestinationDevice> deviceList;

    private static final DestinationDeviceTabList instance = new DestinationDeviceTabList();

    public static DestinationDeviceTabList getInstance() {
        return instance;
    }


    private DestinationDeviceTabList() {
        deviceList = new ArrayList<>();
    }


    public WifiP2pDevice getDevice(int pos) {
        if (pos>=0 && pos <= deviceList.size() - 1) {
            return deviceList.get(pos).getP2pDevice();
        }
        return null;
    }

    public void setDevice(int pos, @NonNull P2pDestinationDevice device) {
        if (pos>=0 && pos <= deviceList.size() - 1) {
            deviceList.set(pos, device);
        } else {
            deviceList.add(pos, device);
        }
    }

    public void addDeviceIfRequired(@NonNull P2pDestinationDevice device) {
        boolean add = true;
        for (P2pDestinationDevice element : deviceList) {
            if (element!=null && element.getP2pDevice() != null && element.getP2pDevice().equals(device.getP2pDevice())) {
                add = false; //already in
            }
        }


        if (add) {

            for (int i = 0; i < deviceList.size(); i++) {
                if (deviceList.get(i) == null) {
                    deviceList.set(i, device);
                    return;
                }
            }


            deviceList.add(device);
        }
    }

    public boolean containsElement(P2pDestinationDevice device) {
        if(device==null) {
            return false;
        }

        for (P2pDestinationDevice element : deviceList) {
            if (element != null && element.getP2pDevice()!=null && element.getP2pDevice().deviceAddress.equals(device.getP2pDevice().deviceAddress)) {
                return true;
            }
        }
        return false;
    }

    public int indexOfElement(P2pDestinationDevice device) {
        if(device==null) {
            return -1;
        }

        for (int i = 0; i < deviceList.size(); i++) {
            if (deviceList.get(i) != null && deviceList.get(i).getP2pDevice()!=null &&
                    deviceList.get(i).getP2pDevice().deviceAddress.equals(device.getP2pDevice().deviceAddress)) {
                return i;
            }
        }
        return -1;
    }

    public int getSize() {
        return deviceList.size();
    }
}
