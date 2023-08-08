package com.littlejoyindia.littlejoyindia.ui.dashboard.home;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import javax.inject.Inject;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.FragmentHomeBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.utils.TopSliderAdapter;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;
import ss.com.bannerslider.Slider;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel>
        implements HomeNavigator {

    public static final String TAG = HomeFragment.class.getSimpleName();


    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    @Inject
    ViewModelProviderFactory factory;
    private HomeViewModel viewModel;

    private FragmentListener fragmentListener;
    private boolean isAttached = false;


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(HomeViewModel.class);
        return viewModel;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentListener = (FragmentListener) context;
            isAttached = true; //flag for whether this framgnet is attache to pager
        } catch (ClassCastException e) {
            throw new ClassCastException(this.toString() + " must implement interface ChoseLocFragment");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // viewModel.getUserProfile();


        Slider slider = getViewDataBinding().topSlider;

        // Slider images
        ArrayList<String> sliderArrayList = new ArrayList<>();
        sliderArrayList.add("https://www.littlejoyindia.com/shopping_banner/ccec720791e0866bb59fdc681f995bf4.jpg");
        sliderArrayList.add("https://www.littlejoyindia.com/shopping_banner/2ee400817447c214a29107c9f39c31cf.jpg");

        /*sliderArrayList.add("http://masterjigroups.com/assets/img/ctet%20social.png");
        sliderArrayList.add("http://masterjigroups.com/assets/img/ctet%20math.png");*/


        slider.setAdapter(new TopSliderAdapter(getActivity(), sliderArrayList));

        CircleIndicator indicator = getViewDataBinding().indicatorTopSlider;
        indicator.createIndicators(4, 0);


    }


    public interface FragmentListener {
        void navigateToSalonServices();

        void navigateToSalonDeals();

        void navigateToOnlineShopping();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void navigateToSalonServices() {
        if (isAttached) {

            getLocationPermission();

            popUpForLadiesVerification();
        }
    }

    @Override
    public void navigateToOnlineShoppingServices() {
        if (isAttached) {
            fragmentListener.navigateToOnlineShopping();
        }
    }

    @Override
    public void navigateToSalonDeals() {
        if (isAttached) {

            getLocationPermission();

            fragmentListener.navigateToSalonDeals();
        }
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void popUpForLadiesVerification() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(requireContext())
                .setMessage("Home Salon Services are only for ladies")
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    fragmentListener.navigateToSalonServices();
                })
                .setNegativeButton("No", null);
        AlertDialog alert = builder1.create();
        alert.show();
        alert.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.GONE);
    }

}
