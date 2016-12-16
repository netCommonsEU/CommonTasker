package com.example.commontasker.discovery.actionlisteners;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import com.example.commontasker.discovery.Configuration;
import com.example.commontasker.discovery.TabFragment;
import com.example.commontasker.discovery.services.ServiceList;
import com.example.commontasker.discovery.services.WiFiP2pService;
import com.example.commontasker.discovery.services.WiFiP2pServicesFragment;
import com.example.commontasker.discovery.services.WiFiServicesAdapter;


public class CustomDnsServiceResponseListener implements WifiP2pManager.DnsSdServiceResponseListener {

    private static final String TAG = "DnsRespListener";

    @Override
    public void onDnsSdServiceAvailable(String instanceName, String registrationType, WifiP2pDevice srcDevice) {
        // A service has been discovered. Is this our app?
        if (instanceName.equalsIgnoreCase(Configuration.SERVICE_INSTANCE)) {

            // update the UI and add the item the discovered device.
            WiFiP2pServicesFragment fragment = TabFragment.getWiFiP2pServicesFragment();
            if (fragment != null) {
                WiFiServicesAdapter adapter = fragment.getMAdapter();
                WiFiP2pService service = new WiFiP2pService();
                service.setDevice(srcDevice);
                service.setInstanceName(instanceName);
                service.setServiceRegistrationType(registrationType);

                ServiceList.getInstance().addServiceIfNotPresent(service);

                if (adapter != null) {
                    adapter.notifyItemInserted(ServiceList.getInstance().getSize() - 1);
                }
                Log.d(TAG, "onDnsSdServiceAvailable " + instanceName);
            }
        }
    }
}
