package com.example.commontasker;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.io.IOException;
import java.util.List;

/**
 * Created by Αρης on 10/10/2016.
 */
public class moirasou3 extends FragmentActivity  {

    /*GoogleMap googleMap;
    MarkerOptions markerOptions;
    LatLng latLng;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moirasou_tab3);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Getting reference to btn_find of the layout activity_main
        Button btn_find = (Button) findViewById(R.id.btn_find);

        // Defining button click event listener for the find button
        View.OnClickListener findClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting reference to EditText to get the user input location
                EditText etLocation = (EditText) findViewById(R.id.et_location);

                // Getting user input location
                String location = etLocation.getText().toString();

                if (location != null && !location.equals("")) {
                    new GeocoderTask().execute(location);
                }
            }
        };

        // Setting button click event listener for the find button
        btn_find.setOnClickListener(findClickListener);

    }

    public void ChangeType(View view) {
        if (googleMap.getMapType() == googleMap.MAP_TYPE_NORMAL) {
            view.startAnimation(buttonClick);
            googleMap.setMapType(googleMap.MAP_TYPE_SATELLITE);
        } else
            view.startAnimation(buttonClick);
        googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);
    }

    public void onZoom(View view) {
        if (view.getId() == R.id.btnzoomin) {
            view.startAnimation(buttonClick);
            googleMap.animateCamera(CameraUpdateFactory.zoomIn());


        }
        if (view.getId() == R.id.btnzoomout) {
            view.startAnimation(buttonClick);
            googleMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED)) {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            Toast.makeText(this, R.string.error_permission_map, Toast.LENGTH_LONG).show();
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    // An AsyncTask class for accessing the GeoCoding Web Service
    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if (addresses == null || addresses.size() == 0) {
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
            }

            // Clears all the existing markers on the map
            googleMap.clear();

            // Adding Markers on Google Map for each matching address
            for (int i = 0; i < addresses.size(); i++) {

                Address address = (Address) addresses.get(i);

                // Creating an instance of GeoPoint, to display in Google Map
                latLng = new LatLng(address.getLatitude(), address.getLongitude());

                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(addressText);

                googleMap.addMarker(markerOptions);

                // Locate the first location
                if (i == 0)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }
    }*/
}

