package com.example.commontasker;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TopPaidFragment extends FragmentActivity   {

    /*GoogleMap googleMap;
    MarkerOptions markerOptions;
    LatLng latLng;
    String location;
    RadioButton rbDriving;
    RadioButton rbBiCycling;
    RadioButton rbWalking;
    RadioGroup rgModes;
    int mMode = 0;
    final int MODE_DRIVING = 0;
    final int MODE_BICYCLING = 1;
    final int MODE_WALKING = 2;
    ArrayList<LatLng> markerPoints;
    TextView tvDistanceDuration;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_top_paid);

        // Getting Google Play availability status

   // Getting reference to rb_driving
        rbDriving = (RadioButton) findViewById(R.id.rb_driving);

        // Getting reference to rb_bicylcing
        rbBiCycling = (RadioButton) findViewById(R.id.rb_bicycling);

        // Getting reference to rb_walking
        rbWalking = (RadioButton) findViewById(R.id.rb_walking);

        // Getting Reference to rg_modes
        rgModes = (RadioGroup) findViewById(R.id.rg_modes);

        rgModes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // Checks, whether start and end locations are captured
                if (markerPoints.size() >= 2) {
                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);
                    location = markerPoints.get(1).toString();
                    // Getting URL to the Google Directions API
                    String url = getDirectionsUrl(origin, dest);

                    DownloadTask downloadTask = new DownloadTask();

                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);
                }
            }
        });

        // Initializing
        markerPoints = new ArrayList<LatLng>();

       // SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

         //Getting a reference to the map
         //googleMap = supportMapFragment.getMap();

       MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
       mapFragment.getMapAsync(this);


        tvDistanceDuration = (TextView) findViewById(R.id.distance_time);

// Setting onclick event listener for the map
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                // Already two locations
                if (markerPoints.size() > 1) {
                    markerPoints.clear();
                    googleMap.clear();
                }

                // Adding new item to the ArrayList
                markerPoints.add(point);

                // Draws Start and Stop markers on the Google Map
                drawStartStopMarkers();

                // Checks, whether start and end locations are captured
                if (markerPoints.size() >= 2) {
                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);

                    // Getting URL to the Google Directions API
                    String url = getDirectionsUrl(origin, dest);

                    DownloadTask downloadTask = new DownloadTask();

                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);
                }
            }
        });
        RadioGroup rgViews = (RadioGroup) findViewById(R.id.rg_views);

        rgViews.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_normal) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                } else if (checkedId == R.id.rb_satellite) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                } else if (checkedId == R.id.rb_terrain) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
            }
        });


        // Getting reference to btn_find of the layout activity_main
        Button btn_find = (Button) findViewById(R.id.btn_find);
        // Defining button click event listener for the find button
        View.OnClickListener findClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting reference to EditText to get the user input location
                EditText etLocation = (EditText) findViewById(R.id.et_location);

                // Getting user input location
                location = etLocation.getText().toString();

                if (location != null && !location.equals("")) {
                    new GeocoderTask().execute(location);
                }

            }
        };

        // Setting button click event listener for the find button
        btn_find.setOnClickListener(findClickListener);

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

    // Drawing Start and Stop locations
    private void drawStartStopMarkers() {

        for (int i = 0; i < markerPoints.size(); i++) {

            // Creating MarkerOptions
            MarkerOptions options = new MarkerOptions();

            // Setting the position of the marker
            options.position(markerPoints.get(i));

            /**
             * For the start location, the color of marker is GREEN and
             * for the end location, the color of marker is RED.
             */
            /*if (i == 0) {
                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            } else if (i == 1) {
                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }

            // Add new marker to the Google Map Android API V2
            googleMap.addMarker(options);
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Travelling Mode
        String mode = "mode=driving";

        if (rbDriving.isChecked()) {
            mode = "mode=driving";
            mMode = 0;
        } else if (rbBiCycling.isChecked()) {
            mode = "mode=bicycling";
            mMode = 1;
        } else if (rbWalking.isChecked()) {
            mode = "mode=walking";
            mMode = 2;
        }

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    /** A method to download json data from url */
    /*private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {

        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }




    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>{

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }


    /** A class to parse the Google Places in JSON format */
   /* private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }*/

        // Executes in UI thread, after the parsing process
       /* @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);

                // Changing the color polyline according to the mode
                if(mMode==MODE_DRIVING)
                    lineOptions.color(Color.RED);
                else if(mMode==MODE_BICYCLING)
                    lineOptions.color(Color.GREEN);
                else if(mMode==MODE_WALKING)
                    lineOptions.color(Color.BLUE);
            }

            if(result.size()<1){
                Toast.makeText(getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            // Drawing polyline in the Google Map for the i-th route
            googleMap.addPolyline(lineOptions);
        }
    }*/





    // A callback method, which is invoked on configuration is changed
    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        // Adding the pointList arraylist to Bundle
        outState.putParcelableArrayList("points", markerPoints);

        // Saving the bundle
        super.onSaveInstanceState(outState);
    }*/





   /* public void onZoom(View view){
        if(view.getId()== R.id.btnzoomin){
            view.startAnimation(buttonClick);
            googleMap.animateCamera(CameraUpdateFactory.zoomIn());

        }
        if(view.getId()== R.id.btnzoomout){
            view.startAnimation(buttonClick);
            googleMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }*/

   /* public float Distance(View view){

        EditText etLocation1 = (EditText) findViewById(R.id.et_location1);
        // Getting user input location
        location1 = etLocation1.getText().toString();

        Location loc1 = new Location(location);
        loc1.getLatitude();
        loc1.getLongitude();

        Location loc2 = new Location(location1);
        loc2.getLatitude();
        loc2.getLongitude();

        view.startAnimation(buttonClick);
        float distanceInMeters = loc1.distanceTo(loc2);
        return distanceInMeters;
    }*/

    // An AsyncTask class for accessing the GeoCoding Web Service
   /* private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

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

            if(addresses==null || addresses.size()==0){
                Toast.makeText(getBaseContext(), "Δεν υπάρχει Διαθέσιμη Διεύθυνση!Παραακαλώ Πληκτρολογήστε την επιθυμητή διεύθυνση", Toast.LENGTH_SHORT).show();
            }

            // Clears all the existing markers on the map
            googleMap.clear();

            // Adding Markers on Google Map for each matching address
            for(int i=0;i<addresses.size();i++){

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
                if(i==0)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }

    }*/


}