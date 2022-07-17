package com.example.furniture_gallery.Activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.furniture_gallery.Core.Language.Language;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelper;
import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;
import com.example.furniture_gallery.R;
import com.example.furniture_gallery.databinding.ActivitySelectMyLocationBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import im.delight.android.location.SimpleLocation;

public class SelectMyLocationActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.OnMapClickListener  {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    ActivitySelectMyLocationBinding selectMyLocationBinding;
    private static String KEY_EMPTY = "";
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    LocationManager locationManager;
    String choosenLocationMyaddress = "";
    String MyLocation ;
    Double choosenLocationLatitude,choosenLocationLongtitude;
    FragmentTransaction transaction;
    SimpleLocation location;
    List<String> SelectTask  = new ArrayList<>();
    private static final int REQUEST_LOCATION = 1;
    private static final int PERMISSION_REQUEST_CODE = 7001;
    PreferenceHelper preferenceHelper;
    PreferenceHelperChoseLanguage preferenceHelperChoseLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelperChoseLanguage = PreferenceHelperChoseLanguage.getInstans(this);
        Language.changeLanguage(this, preferenceHelperChoseLanguage.getLang());
        selectMyLocationBinding = ActivitySelectMyLocationBinding.inflate(getLayoutInflater());
        setContentView(selectMyLocationBinding.getRoot());

        preferenceHelper = new PreferenceHelper(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapGetLocationMap);
        mapFragment.getMapAsync(this);


        boolean requireFineGranularity = false;
        boolean passiveMode = false;
        long updateIntervalInMilliseconds = 5 * 1000;
        boolean requireNewLocation = false;
        location = new SimpleLocation(this, requireFineGranularity, passiveMode, updateIntervalInMilliseconds, requireNewLocation);


        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        selectMyLocationBinding.tvContinueSelectMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectMyLocationBinding.TvTextMyLocationLatAndLng.getText().toString().isEmpty()) {
                    Toast.makeText(SelectMyLocationActivity.this, "please select your location", Toast.LENGTH_SHORT).show();
                }else {
                  //  Toast.makeText(SelectMyLocationActivity.this, choosenLocationLatitude + " " + choosenLocationLongtitude, Toast.LENGTH_SHORT).show();
                    preferenceHelper.putChooseLocationLatitude(String.valueOf(choosenLocationLatitude));
                    preferenceHelper.putChooseLocationLngtitude(String.valueOf(choosenLocationLongtitude));
                    startActivity(new Intent(SelectMyLocationActivity.this,HomeMainActivity.class));
                    finish();
                }
                
            }
        });

    }

    private void init() {

        Log.e("Init", "init");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMISSION_REQUEST_CODE);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            Location location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            if (location != null) {
                Log.e("myLocation", "Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());

                displayCurrentLocation(location);

            }

        }

    }


    private void displayCurrentLocation(Location location) {
        mMap.clear();
        LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(myLocation).title("").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//        mMap.addMarker(new MarkerOptions().position(myLocation).title("").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_place_24px)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15.0f));
        getLocationAddress(location.getLatitude(), location.getLongitude());

    }

    private void getLocationAddress(double latitude, double longitude) {


        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address result;

            if (addresses != null && !addresses.isEmpty()) {

                Log.e("MagedMyLocation", addresses.get(0).getCountryCode() + " " + addresses.get(0));
                Log.e("MagedMyLocation", addresses + " " + addresses.get(0));

//                choosenLocation=addresses.get(0).getAddressLine(0)+"";
//                setMarkerWithLocation(new LatLng(latitude,longitude),choosenLocation);
                // Log.e("MagedMyLocation0",choosenLocation+" "+addresses.get(0));

                String state = addresses.get(0).getAdminArea();
                String mntqa = addresses.get(0).getSubAdminArea();
                String country = addresses.get(0).getCountryName();
                String addressLine = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getAddressLine(0);


                choosenLocationMyaddress =  addressLine;
                setMarkerWithLocation(new LatLng(latitude, longitude), choosenLocationMyaddress);
                Double lat = addresses.get(0).getLatitude();
                Double lang = addresses.get(0).getLongitude();

                choosenLocationLatitude = latitude;
                choosenLocationLongtitude = longitude;
                MyLocation = String.valueOf(choosenLocationLatitude + choosenLocationLongtitude);

                selectMyLocationBinding.TvMyLocationMap.setText( choosenLocationMyaddress);
                selectMyLocationBinding.TvTextMyLocationLatAndLng.setText("your location is" + " "+ MyLocation);
                Log.e("MyLocation", " " + addressLine);
                Log.e("MyLocation", " " + choosenLocationLatitude + " "  + choosenLocationLongtitude);



//                Toast.makeText(this, country, Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, city, Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, String.valueOf(choosenLocationLatitude), Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, String.valueOf(choosenLocationLongtitude), Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, choosenLocationMyaddress, Toast.LENGTH_SHORT).show();

//                choosenLocationLatitude=String.valueOf(latitude);
//                choosenLocationLongtitude=String.valueOf(longitude);
//                if (whichActivity==1){
//                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("countrycodes", addresses.get(0).getCountryCode());
//                    editor.apply();
//                }

            }
        } catch (IOException ignored) {
            //do something
        }
    }

    private void setMarkerWithLocation(LatLng latLng, String choosenLocation) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).title(choosenLocation).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))).showInfoWindow();
//        mMap.addMarker(new MarkerOptions().position(myLocation).title("").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_place_24px)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e("OnMapReady", "OnMapReady");
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        //   LatLng maka = new LatLng(21.422510, 39.826168);

//        if (location.getLatitude() ==null && location.getLongitude() =null){
//            LatLng maka = new LatLng(21.422510, 39.826168);
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(maka));
//            //  mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(maka, 12));
//        }


        LatLng maka = new LatLng(30.0596185, 31.3285051);

        selectMyLocationBinding.ImageGetMyLocationSelectMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(SelectMyLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(SelectMyLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SelectMyLocationActivity.this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    }, PERMISSION_REQUEST_CODE);
                } else {
                    checkGpsOpened();
                    openMyLocationButton();
                    //  checkGpsOpened();
                }
            }
        });

        mMap.moveCamera(CameraUpdateFactory.newLatLng(maka));
        //  mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(maka, 12));
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setBuildingsEnabled(true);
        // mMap.getUiSettings().setMyLocationButtonEnabled(true);
        // mMap.getUiSettings().isMyLocationButtonEnabled();
        // googleMap.isMyLocationEnabled();
//        if (locationButton != null &&
//                locationButton.findViewById(Integer.parseInt("1")) != null) {
//            // Get the button view
//            View locationButton = ((View) this.locationButton.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
//            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
//// position on right bottom
//            rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
//            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//            rlp.setMargins(0, 0, 0, 0);
//        }

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {

                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(point).title("").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                //  mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
                getLocationAddress(point.latitude, point.longitude);
                //  Tv_MyLocation.setText("your location is" + " "+ choosenLocationMyaddress);
                //   Toast.makeText(GetLocationUserMapsActivity.this, String.valueOf(point.latitude+" -- "+ point.longitude), Toast.LENGTH_SHORT).show();
            }
        });

//        View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
//        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
//// position on right bottom
//        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
//        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
//        rlp.setMargins(0, 180, 180, 0);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMISSION_REQUEST_CODE);
        } else {
            checkGpsOpened();
            openMyLocationButton();
            //  checkGpsOpened();
        }
//        init();

        Log.e("location", "onMapready");
    }

    private void openMyLocationButton() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        // mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationClickListener(new GoogleMap.OnMyLocationClickListener() {
            @Override
            public void onMyLocationClick(@NonNull Location location) {
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    Log.e("OpenAlert", "2");
                    checkForGPSIsopened();
                }
                if (location != null) {
                    Log.e("myLocation", location.getLongitude() + "");
                    displayCurrentLocation(location);
                }
            }
        });
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    Log.e("OpenAlert", "2");
                    checkForGPSIsopened();
                }
               /* else {
                    init();
                    init();
                    init();
                    init();
                    init();
                    init();
                    init();
                    init();
                }*/

                return false;
            }
        });
    }

    private void checkGpsOpened() {
        String provider = android.provider.Settings.Secure.getString(
                getContentResolver(),
                android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps")) {
            //  Toast.makeText(this, "GPS is Closed", Toast.LENGTH_SHORT).show();
            checkForGPSIsopened();
        } else {
            init();

        }
    }

    private void checkForGPSIsopened() {

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API).addConnectionCallbacks(SelectMyLocationActivity.this)
                    .addOnConnectionFailedListener(SelectMyLocationActivity.this).build();
            googleApiClient.connect();
        }
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        // **************************
        builder.setAlwaysShow(true); // this is the key ingredient
        // **************************

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
                .checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result
                        .getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can
                        // initialize location
                        Log.e("location", "GPSOPEEEEND");
                        init();
                        // requests here.
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be
                        // fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling
                            // startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(SelectMyLocationActivity.this, 1000);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have
                        // no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });


        //--------------------------------------------------
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                Log.e("UserGPS", "OpenGps");
                init();
            }
            if (resultCode == RESULT_CANCELED) {
                Log.e("UserGPS", "cancel");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkGpsOpened();
                openMyLocationButton();

            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

}