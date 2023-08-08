package com.littlejoyindia.littlejoyindia.utils;

import com.google.android.gms.maps.model.LatLng;

public class Events {

    // Event used to send message from fragment to activity.
    public static class FragmentActivityMessage {
        private String message;
        public FragmentActivityMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

    // Event used to send message from activity to fragment.
    public static class ActivityFragmentMessage {
        private String message;
        public ActivityFragmentMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

    // Event used to send message from activity to activity.
    public static class ActivityActivityMessage {
        private String message;
        public ActivityActivityMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

    // Event used to send messactivity to activity.
    public static class FragmentFragmentMessage {
        private String address;
        private LatLng mLatLng;
        public FragmentFragmentMessage(String address, LatLng mLatLng) {
            this.address = address;
            this.mLatLng = mLatLng;
        }
        public String getAddress() {
            return address;
        }

        public LatLng getmLatLng() {
            return mLatLng;
        }
    }

    public static class SetRefereshToFragment {

        private boolean isReferesh;

        public SetRefereshToFragment(boolean isReferesh) {
            this.isReferesh = isReferesh;
        }

        public boolean isReferesh() {
            return isReferesh;
        }

    }

    public static class SetRefereshToFragmentWithId {

        private boolean isReferesh;

        public String getType() {
            return type;
        }

        private String type;

        public SetRefereshToFragmentWithId(boolean isReferesh, String type) {
            this.isReferesh = isReferesh;
            this.type = type;
        }

        public boolean isReferesh() {
            return isReferesh;
        }

    }


}
