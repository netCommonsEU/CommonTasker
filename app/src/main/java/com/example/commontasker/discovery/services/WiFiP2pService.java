package com.example.commontasker.discovery.services;

import android.net.wifi.p2p.WifiP2pDevice;

import lombok.Getter;
import lombok.Setter;

/**
 * A structure to hold service information.
 */
public class WiFiP2pService {
    @Setter private WifiP2pDevice device;
    @Getter @Setter private String instanceName = null;
    @Getter @Setter private String serviceRegistrationType = null;

    public String getInstanceName() {
        return instanceName;
    }

    public WifiP2pDevice getDevice() {
        return device;
    }

    public void setDevice(WifiP2pDevice device) {
        this.device = device;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public void setServiceRegistrationType(String serviceRegistrationType) {
        this.serviceRegistrationType = serviceRegistrationType;
    }
}
