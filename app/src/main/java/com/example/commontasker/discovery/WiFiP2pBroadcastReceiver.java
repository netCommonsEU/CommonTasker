
package com.example.commontasker.discovery;



import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.util.Log;

import com.example.commontasker.discovery.model.LocalP2PDevice;


public class WiFiP2pBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "P2pBroadcastReceiver";

    private final WifiP2pManager manager;
    private final Channel channel;
    private final Activity activity;

    /**
     * Constructor to set some parameters.
     * @param manager WifiP2pManager system service
     * @param channel Wifi p2p channel
     * @param activity Activity associated with the receiver
     */
    public WiFiP2pBroadcastReceiver(WifiP2pManager manager, Channel channel, Activity activity) {
        super();
        this.manager = manager;
        this.channel = channel;
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        Log.d(TAG, action);

        if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

            if (manager == null) {
                return;
            }

            NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

            if (networkInfo.isConnected()) {

                // we are connected with the other device, request connection
                // info to find group owner IP
                Log.d(TAG,"Connected to p2p network. Requesting network details");

                manager.requestConnectionInfo(channel,(ConnectionInfoListener) activity);

                ((WifiActivity)activity).setConnected(true);

                //now color the active tabs
                ((WifiActivity)activity).addColorActiveTabs(false);

                //and change the visible tab
                ((WifiActivity)activity).setTabFragmentToPage(((WifiActivity)activity).getTabNum());

            } else {
                // It's a disconnect, i need to restart the discovery phase

                Log.d(TAG, "Disconnect. Restarting discovery");

                //remove color on all tabs
                ((WifiActivity)activity).addColorActiveTabs(true);

                //if manualItemMenuDisconnectAndStartDiscovery() is not activated by the user
                if(!((WifiActivity)activity).isBlockForcedDiscoveryInBroadcastReceiver()) {

                    //force stop discovery process
                    ((WifiActivity) activity).forceDiscoveryStop();
                    ((WifiActivity) activity).restartDiscovery();
                }

                //disable all chatmanagers
                ((WifiActivity)activity).setDisableAllChatManagers();

                //change the visible tab to the first one, because i want to see the available services to reconnect, in necessary
                ((WifiActivity)activity).setTabFragmentToPage(0);

                ((WifiActivity)activity).setConnected(false);

                //to be sure that the GO icon inside the local device cardview is removed, i call the method to hide this icon
                TabFragment.getWiFiP2pServicesFragment().hideLocalDeviceGoIcon();

                //to be sure that the ip address inside the local device cardview is removed
                TabFragment.getWiFiP2pServicesFragment().resetLocalDeviceIpAddress();
            }

        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {

            //if is not a disconnect, neither a connect, set the local device
            WifiP2pDevice device = intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE);
            LocalP2PDevice.getInstance().setLocalDevice(device);
            Log.d(TAG, "Local Device status -" + device.status);

        }
    }
}
