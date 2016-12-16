package com.example.commontasker.discovery.model;

import android.net.wifi.p2p.WifiP2pDevice;

import lombok.Getter;
import lombok.Setter;

public class P2pDestinationDevice {

    @Getter
    private WifiP2pDevice p2pDevice;
    @Getter @Setter
    private String destinationIpAddress; //it's the ip address


    public P2pDestinationDevice(WifiP2pDevice p2pDevice) {
        this.p2pDevice = p2pDevice;
    }

    public P2pDestinationDevice() {
    }

    @Override
    public String toString() {
        return this.p2pDevice.deviceName + ", " + this.p2pDevice.deviceAddress + ", " + this.p2pDevice.status;
    }

    public WifiP2pDevice getP2pDevice() {
        return p2pDevice;
    }

    public void setDestinationIpAddress(String destinationIpAddress) {
        this.destinationIpAddress = destinationIpAddress;
    }

    public String getDestinationIpAddress() {
        return destinationIpAddress;
    }
}
