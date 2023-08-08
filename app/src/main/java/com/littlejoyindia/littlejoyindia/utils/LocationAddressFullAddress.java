package com.littlejoyindia.littlejoyindia.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationAddressFullAddress {
    private static final String TAG = "LocationAddress";

    public  void getAddressFromLocation(final double latitude, final double longitude,
                                              final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                Address address = null;
                try {
                    List<Address> addressList = geocoder.getFromLocation(
                            latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();

                        if (address.getMaxAddressLineIndex() > 0) {
                            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                                sb.append(address.getAddressLine(i));
                            }
                        } else {
                            try {
                                sb.append(address.getAddressLine(0));
                            } catch (Exception ignored) {}
                        }

                  /*      Address[
                                addressLines=[0:"HCHR+F54, Bisrakh Jalalpur, Greater Noida, Uttar Pradesh 201306, India"],
                        feature=HCHR+F54,
                                admin=Uttar Pradesh,
                                sub-admin=Meerut Division,
                                locality=Greater Noida,
                                thoroughfare=null,
                                postalCode=201306,
                                countryCode=IN,
                                countryName=India,
                                hasLatitude=true,latitude=28.578659299999998,hasLongitude=true,longitude=77.4404472,
                                phone=null,url=null,extras=null]*///

                        result = sb.toString();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Unable connect to Geocoder", e);
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result != null) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
//                        result = "Latitude: " + latitude + " Longitude: " + longitude +
//                                "\n\nAddress:\n" + result;
                        bundle.putString("address", result);
                        bundle.putString("house_no", address.getFeatureName());
                        bundle.putString("pincode", address.getPostalCode());
                        bundle.putString("landmark", address.getSubLocality());
                        bundle.putString("city", address.getLocality());
                        bundle.putString("state", address.getAdminArea());
                        message.setData(bundle);
                    } else {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "Latitude: " + latitude + " Longitude: " + longitude +
                                "\n Unable to get address for this lat-long.";
                        bundle.putString("address", "Not Found");
                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }
}