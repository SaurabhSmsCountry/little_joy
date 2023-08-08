package com.littlejoyindia.littlejoyindia.ui.dashboard.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ResultReceiver;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.ActivityMapUIBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.utils.AppLocationService;
import com.littlejoyindia.littlejoyindia.utils.AppUtils;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;
import com.littlejoyindia.littlejoyindia.utils.FetchAddressIntentService;
import com.littlejoyindia.littlejoyindia.utils.LocationAddressFullAddress;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


public class MapUIActivity extends BaseActivity<ActivityMapUIBinding, MapUIViewModel>
        implements MapUINavigator, OnMapReadyCallback {

    private LocationManager locationManager;
    private LocationListener locationListener;
    AppLocationService appLocationService;
    private Context context;

    AlertDialog alert;


    public static final String TAG = MapUIActivity.class.getSimpleName();
    Location mLocationSent = null;

    private GoogleMap map;
    private LocationCallback locationCallback;
    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;

    // not granted.
    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;
    private Location lastKnownLocation;

    private boolean requestingLocationUpdates = false;

    private LatLng mCurrentLatlng;
    private String pincode = "";
    private String userArea = "";
    private String houseNo = "";
    private String streetNo = "";
    private String landmark = "";

    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;

    /**
     * Receiver registered with this activity to get the response from FetchAddressIntentService.
     */
    private AddressResultReceiver mResultReceiver;
    /**
     * The formatted location address.
     */
    protected String mAddressOutput;
    protected String mAreaOutput;
    protected String mCityOutput;
    protected String mStateOutput;
    protected String userState = "";
    protected String userDistrict = "";

    View mapView;


    ActivityMapUIBinding mapUIBinding;

    @Inject
    ViewModelProviderFactory factory;

    private MapUIViewModel mapUIViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_map_u_i;
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, MapUIActivity.class);
    }

    @Override
    public MapUIViewModel getViewModel() {
        mapUIViewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(MapUIViewModel.class);
        return mapUIViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapUIBinding = getViewDataBinding();

        context = this;

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context)
                .setMessage("Please enable GPS")
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .setNegativeButton("No", null);
        alert = builder1.create();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLocationUpdate();
        } else {
            requestLocationPermission();
        }

        setUpToolBar();
        mapUIViewModel.setNavigator(this);

        mResultReceiver = new AddressResultReceiver(new Handler());

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapView = mapFragment.getView();

        mapFragment.getMapAsync(this);


        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                updateLocationUI();
                for (Location location : locationResult.getLocations()) {
                    Log.e("Update", "Yes");
                }
            }
        };

        new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                stopLocationUpdates();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                1059);
    }

    private void requestLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationListener = new LocationListener() {
            float accuracy = 0;
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location",location.toString());
                Log.i("GetAccuracy", String.valueOf(location.getAccuracy()));

                if (accuracy < location.getAccuracy())
                    accuracy = location.getAccuracy();

                if (location.getAccuracy() >= accuracy && location.getAccuracy() >= 10) {
                    LocationAddressFullAddress locationAddress = new LocationAddressFullAddress();
                    locationAddress.getAddressFromLocation(location.getLatitude(), location.getLongitude(),
                            MapUIActivity.this, new GeocoderHandler());
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                requestLocationUpdate();
            }

            @Override
            public void onProviderEnabled(String s) {
                requestLocationUpdate();
            }

            @Override
            public void onProviderDisabled(String s) {
                if (alert.isShowing())
                    alert.dismiss();
                alert.show();
                alert.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.GONE);
            }
        };

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 5, locationListener, null);
    }

    private void setUpToolBar() {

        setSupportActionBar(mapUIBinding.toolbarLayout.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Delivery Address");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {
            Place place = Autocomplete.getPlaceFromIntent(data);
            Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());

            if (place.getLatLng() != null) {
                map.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
                mCurrentLatlng = place.getLatLng();

                Location mLocation = new Location("");
                mLocation.setLatitude(place.getLatLng().latitude);
                mLocation.setLongitude(place.getLatLng().longitude);
                //startIntentService(mLocation);
                //getAddressFRomLocation(mLocation);
            }


        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            // TODO: Handle the error.
            Status status = Autocomplete.getStatusFromIntent(data);
            Log.i(TAG, status.getStatusMessage());
        } else if (resultCode == RESULT_CANCELED) {
            // The user canceled the operation.
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // map.getUiSettings().setMyLocationButtonEnabled(false);

//        map.setMapStyle(
//                MapStyleOptions.loadRawResourceStyle(
//                        this, R.raw.tsaw_style));

        map.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLng center = map.getCameraPosition().target;
                Location mLocation = new Location("");
                mLocation.setLatitude(center.latitude);
                mLocation.setLongitude(center.longitude);
                mCurrentLatlng = center;
                //  startIntentService(mLocation);
                //getAddressFRomLocation(mLocation);
            }
        });

        // Prompt the user for permission.
        getLocationPermission();

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();


        if (mapView != null &&
                mapView.findViewById(Integer.parseInt("1")) != null) {
            // Get the button view
            View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            // and next place it, on bottom right (as Google Maps app)
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    locationButton.getLayoutParams();
            // position on right bottom
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.setMargins(0, 0, 30, 500);

        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }


    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        locationManager.removeUpdates(locationListener);
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        requestingLocationUpdates = true;

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
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                map.setMyLocationEnabled(true);
                //  map.getUiSettings().setMyLocationButtonEnabled(false);

                if (!requestingLocationUpdates) {
                    startLocationUpdates();
                }
            } else {
                map.setMyLocationEnabled(false);
                // map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                //  getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            lastKnownLocation = task.getResult();
                            if (lastKnownLocation != null) {
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lastKnownLocation.getLatitude(),
                                                lastKnownLocation.getLongitude()), DEFAULT_ZOOM));

                                mCurrentLatlng = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                                //  startIntentService(lastKnownLocation);

                                //getAddressFRomLocation(lastKnownLocation);

                            }
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            map.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                            //  map.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("PermissionCalled", "Yes");
        locationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                }
            }
        }
        if (!requestingLocationUpdates) {
            startLocationUpdates();
            requestLocationUpdate();
        } else {
            requestLocationPermission();
        }

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
    }

    @Override
    public void onClickSetLocation() {
        String address = getViewDataBinding().ftv.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("address", address);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        //viewModel.setLocationByUser(userArea, houseNo, streetNo, pincode, landmark, mCurrentLatlng.latitude, mCurrentLatlng.longitude);

    }

    @Override
    public void onClickConfirm() {


        String userHouseNo = getViewDataBinding().etHouseNo.getText().toString();
        String fullAddress = getViewDataBinding().etFullAddress.getText().toString();
//        String userStreetNo = getViewDataBinding().etStreetNo.getText().toString();
        String userPincode = getViewDataBinding().etPincode.getText().toString();
        String userLandmark = getViewDataBinding().etLandmark.getText().toString();
        String useretState = getViewDataBinding().etState.getText().toString();
        String useretDistrict = getViewDataBinding().etDistrict.getText().toString();
        String city = mCityOutput;

        String lat = "78428032";
        String lng = "4080398490892";


        if (CommonUtils.checkForEmptyField(fullAddress)) {
            CommonUtils.toastMe("[ Please add full address details ]", this);
            return;
        }

        if (CommonUtils.checkForEmptyField(userHouseNo)) {
            CommonUtils.toastMe("[ House No ] is required", this);
            return;
        }

        /*if (mLocationSent!=null){
            lat = ""+mLocationSent.getLatitude();
            lng = ""+mLocationSent.getLongitude();
        }*/


        /*if( CommonUtils.checkForEmptyField(lat)){
            CommonUtils.toastMe("[ Location Not Found ] is required",this);
            return;
        }*/

//        if( userPincode.length() != 6){
//            CommonUtils.toastMe("[ Pincode ] is 6 digit",this);
//            return;
//        }

        if (CommonUtils.checkForEmptyField(userLandmark)) {
            CommonUtils.toastMe("[ Landmark ] is required", this);
            return;
        }

        if (CommonUtils.checkForEmptyField(useretDistrict)) {
            CommonUtils.toastMe("[ City ] is required", this);
            return;
        }

        if (CommonUtils.checkForEmptyField(useretState)) {
            CommonUtils.toastMe("[ State ] is required", this);
            return;
        }


        // pincode = userPincode;
        houseNo = userHouseNo;
        //   streetNo = userStreetNo;
        landmark = userLandmark;

        //  userState = useretState;
        // userDistrict = useretDistrict;

        //  String address = houseNo + ", "+streetNo  + ", "+landmark +", "+userDistrict +", "+userState +", "+pincode + ", India";

        getViewDataBinding().ftv.setText(fullAddress);
        getViewDataBinding().llAddress.setVisibility(View.GONE);
        getViewDataBinding().rlConfirmAddress.setVisibility(View.VISIBLE);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("address", fullAddress);
        returnIntent.putExtra("houseNo", userHouseNo);
        returnIntent.putExtra("latitude", lat);
        returnIntent.putExtra("longitude", lng);
        returnIntent.putExtra("city", city);
        returnIntent.putExtra("pincode", userPincode);
        //  returnIntent.putExtra("streetNo",streetNo);
        returnIntent.putExtra("landmark", userLandmark);
        returnIntent.putExtra("userState", useretState);
        returnIntent.putExtra("userDistrict", useretDistrict);

        mapUIViewModel.checkCodIsAvailable(userPincode, returnIntent);


    }

    @Override
    public void onClickEdit() {
        getViewDataBinding().llAddress.setVisibility(View.GONE);
        getViewDataBinding().rlConfirmAddress.setVisibility(View.VISIBLE);

    }


    @Override
    public void onClickSearch() {
        openAutocompleteActivity();
    }

    @Override
    public void updateCODUI(String msg, boolean isAvailable, Intent returnIntent) {
        if (!isAvailable){
            final Toast toast = Toast.makeText(getApplicationContext(), msg + " For the new added address", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 4000);
        }
        returnIntent.putExtra("isAvailable", isAvailable);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    /**
     * Receiver for data sent from FetchAddressIntentService.
     */
    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        /**
         * Receives data sent from FetchAddressIntentService and updates the UI in MainActivity.
         */
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Display the address string or an error message sent from the intent service.
            mAddressOutput = resultData.getString(AppUtils.LocationConstants.RESULT_DATA_KEY);
            //  mAreaOutput = resultData.getString(AppUtils.LocationConstants.LOCATION_DATA_AREA);
            mCityOutput = resultData.getString(AppUtils.LocationConstants.LOCATION_DATA_CITY);
            //  streetNo = resultData.getString(AppUtils.LocationConstants.LOCATION_DATA_STREET);
            //   pincode =  resultData.getString(AppUtils.LocationConstants.LOCATION_DATA_PIN_CODE);

            // mStateOutput = resultData.getString(AppUtils.LocationConstants.LOCATION_DATA_STREET);
            //   pincode =  resultData.getString(AppUtils.LocationConstants.LOCATION_DATA_PIN_CODE);

            //  userState =  resultData.getString(AppUtils.LocationConstants.LOCATION_State);
            //  userDistrict = resultData.getString(AppUtils.LocationConstants.LOCATION_district);

            getViewDataBinding().tvAddress.setText(mAddressOutput);
            // displayAddressOutput();

            // Show a toast message if an address was found.
            if (resultCode == AppUtils.LocationConstants.SUCCESS_RESULT) {
                //  showToast(getString(R.string.address_found));

            }
        }

    }


    /**
     * Updates the address in the UI.
     */
    protected void displayAddressOutput() {
        //  mLocationAddressTextView.setText(mAddressOutput);
        try {
            // if (mAreaOutput != null)
            // mLocationText.setText(mAreaOutput+ "");

            getViewDataBinding().etPincode.setText(pincode);
            getViewDataBinding().etHouseNo.setText(streetNo);

            getViewDataBinding().etState.setText(userState);
            getViewDataBinding().etDistrict.setText(userDistrict);

            getViewDataBinding().etLandmark.setText(mAreaOutput);

            getViewDataBinding().llAddress.setVisibility(View.GONE);
            getViewDataBinding().rlConfirmAddress.setVisibility(View.VISIBLE);

            getViewDataBinding().etFullAddress.setText(mAddressOutput);
            //mLocationText.setText(mAreaOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    mAddressOutput = bundle.getString("address");

                    if (mAddressOutput.equalsIgnoreCase("Not Found")) {
                        Log.e("locationAddress", "a : " + mAddressOutput);
                        startIntentService(mLocationSent);
                    } else {
                        Log.e("locationAddress", "a : " + mAddressOutput);
                        getViewDataBinding().tvAddress.setText(mAddressOutput);

                        mAreaOutput = bundle.getString("landmark");
                        mCityOutput = bundle.getString("");
                        streetNo = bundle.getString("house_no");
                        pincode =  bundle.getString("pincode");
                        mStateOutput = bundle.getString("");
                        userState =  bundle.getString("state");
                        userDistrict = bundle.getString("city");
                    }


                     displayAddressOutput();

                    break;
                default:
                    startIntentService(mLocationSent);
                    //locationAddress = "Saloon at Home";
            }
        }
    }


    /**
     * Creates an intent, adds location data to it as an extra, and starts the intent service for
     * fetching an address.
     */
    protected void startIntentService(Location mLocation) {

        mLocationSent = mLocation;

        // Create an intent for passing to the intent service responsible for fetching the address.
        Intent intent = new Intent(this, FetchAddressIntentService.class);

        // Pass the result receiver as an extra to the service.
        intent.putExtra(AppUtils.LocationConstants.RECEIVER, mResultReceiver);

        // Pass the location data as an extra to the service.
        intent.putExtra(AppUtils.LocationConstants.LOCATION_DATA_EXTRA, mLocation);

        // Start the service. If the service isn't already running, it is instantiated and started
        // (creating a process for it if needed); if it is running then it remains running. The
        // service kills itself automatically once all intents are processed.
        startService(intent);
    }


    /*public void getAddressFRomLocation(Location gpsLocation) {
        mLocationSent = gpsLocation;

        appLocationService = new AppLocationService(MapUIActivity.this);

        if (gpsLocation != null) {
            double latitude = gpsLocation.getLatitude();
            double longitude = gpsLocation.getLongitude();
            String result = "Latitude: " + gpsLocation.getLatitude() +
                    " Longitude: " + gpsLocation.getLongitude();
            Log.e("result", "a : " + result);


            LocationAddressFullAddress locationAddress = new LocationAddressFullAddress();
            locationAddress.getAddressFromLocation(latitude, longitude,
                    MapUIActivity.this, new GeocoderHandler());
        }
    }*/


    private void openAutocompleteActivity() {
        // return after the user has made a selection.
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);

        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
                .build(this);
        startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
    }
}