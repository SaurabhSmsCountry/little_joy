package com.littlejoyindia.littlejoyindia.ui.dashboard.salon;

import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonServiceModel;
import com.littlejoyindia.littlejoyindia.databinding.SalonFragmentBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.cart.CartActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.home.HomeFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.map.MapUIActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat.SalonCatFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat.Services;
import com.littlejoyindia.littlejoyindia.utils.AppLocationService;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;
import com.littlejoyindia.littlejoyindia.utils.LocationAddress;
import com.littlejoyindia.littlejoyindia.utils.LocationAddressFullAddress;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class SalonFragment extends BaseFragment<SalonFragmentBinding, SalonViewModel>
        implements SalonNavigator {

    public static final String TAG = SalonFragment.class.getSimpleName();


    public static List<SalonServiceModel> salonServiceModelList = new ArrayList<>();

    AlertDialog alertGPS, alert, alertServiceArea;

    @Inject
    ViewModelProviderFactory factory;
    private SalonViewModel viewModel;


    @Inject
    CategoryPagerAdapter mPagerAdapter;

    private FragmentListener fragmentListener;
    private boolean isAttached = false;
    private SalonFragmentBinding mBinding;

    AppLocationService appLocationService;

    private String userDistrict = null;
    private LocationManager locationManager;
    private LocationListener locationListener;


    public static SalonFragment newInstance() {
        Bundle args = new Bundle();
        SalonFragment fragment = new SalonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.salon_fragment;
    }

    @Override
    public SalonViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(SalonViewModel.class);
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

        appLocationService = new AppLocationService(requireContext());

        AlertDialog.Builder builder1 = new AlertDialog.Builder(requireContext())
                .setMessage("Please enable GPS")
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .setNegativeButton("No", null);
        alertGPS = builder1.create();

        AlertDialog.Builder builder2 = new AlertDialog.Builder(requireContext())
                .setMessage("Minimum Booking Amount for Home Services is 999/-\nPlease Add More Services.")
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .setNegativeButton("No", null);
        alert = builder2.create();

        AlertDialog.Builder builder3 = new AlertDialog.Builder(requireContext())
                .setMessage("Sorry our service is not available in your Area/Locality.")
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .setNegativeButton("No", null);
        alertServiceArea = builder3.create();

        locationManager = (LocationManager) requireContext().getSystemService(LOCATION_SERVICE);


//        try {
            requestLocationUpdate();
            /* appLocationService = new AppLocationService(getActivity());
            Location gpsLocation = appLocationService
                    .getLocation(LocationManager.NETWORK_PROVIDER);
            if (gpsLocation != null) {
                double latitude = gpsLocation.getLatitude();
                double longitude = gpsLocation.getLongitude();
                String result = "Latitude: " + gpsLocation.getLatitude() +
                        " Longitude: " + gpsLocation.getLongitude();
                Log.e("result", "a : " + result);


                LocationAddress locationAddress = new LocationAddress();
                locationAddress.getAddressFromLocation(latitude, longitude,
                        getActivity(), new GeocoderHandler());*/
//            }
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
    }

    private void requestLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location",location.toString());
                Log.i("GetAccuracy", String.valueOf(location.getAccuracy()));

                LocationAddressFullAddress locationAddress = new LocationAddressFullAddress();
                locationAddress.getAddressFromLocation(location.getLatitude(), location.getLongitude(),
                        requireContext(), new GeocoderHandler());
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
                if (alertGPS.isShowing())
                    alertGPS.dismiss();
                alertGPS.show();
                alertGPS.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.GONE);
            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5, locationListener);
    }

    public void updateParentListData(Services serviceModel,
                                     int qty,
                                     int isAddedToCart,
                                     int salonModelPosition) {

        for (int i = 0; i < salonServiceModelList.get(salonModelPosition).getServices().size(); i++) {
            if (salonServiceModelList.get(salonModelPosition).getServices().get(i).getId().equals(serviceModel.getId())) {
                salonServiceModelList.get(salonModelPosition).getServices().get(i).setIsAddedInCart(isAddedToCart);
                salonServiceModelList.get(salonModelPosition).getServices().get(i).setQuantity(qty);

                break;
            }
        }


    }


    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    userDistrict = bundle.getString("city");
                    Log.e("locationAddress", "a : " + locationAddress);
                    viewModel.getSaloonServiceCity();
                    break;
                default:
                    locationAddress = "Saloon at Home";
            }

            if (isAttached) {
                fragmentListener.setHeaderText(locationAddress);
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();
        viewModel.getCategoryAndSubCategories();

        mBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View v = tab.getCustomView();
                if (v != null) {
                    int i = tab.getPosition();
                    changeTabItemView(v, salonServiceModelList.get(i),
                            salonServiceModelList.get(i).getCat_active_icon(), true
                    );
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int i = tab.getPosition();
                View v = tab.getCustomView();
                changeTabItemView(v, salonServiceModelList.get(i),
                        salonServiceModelList.get(i).getCat_normal_icon(), false
                );
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        getViewDataBinding().llFooter.setOnClickListener(v -> {
            if (Integer.parseInt(getViewDataBinding().tvPrice.getText().toString().replace("\u20b9", "")) < 999) {
                if (alert.isShowing())
                    alert.dismiss();
                alert.show();
                alert.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.GONE);
            } else {
                Intent mIntent = CartActivity.newIntent(getActivity());
                mIntent.putExtra("cartType", 1);
                someActivityResultLauncher.launch(mIntent);
            }
        });

    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                viewModel.getCategoryAndSubCategories();
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    Log.e("@TAG", "Get Result");
                }
            });

    public interface FragmentListener {
        void setHeaderText(String headerText);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void setCategoryAndSubCategoryUI(List<SalonServiceModel> data) {


        ArrayList<String> mArrayListSubCats = new ArrayList<>();
        ArrayList<SalonServiceModel.Service> mServiceArrayList = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {

            mArrayListSubCats.addAll(data.get(i).getGetSub());
            mServiceArrayList.addAll(data.get(i).getServices());

        }


        SalonServiceModel serviceModel = new SalonServiceModel();
        serviceModel.setCat("All");
        serviceModel.setGetSub(mArrayListSubCats);
        serviceModel.setServices(mServiceArrayList);
        data.add(0, serviceModel);


        salonServiceModelList = data;
        mBinding.viewPager.setSaveFromParentEnabled(false);
        setupViewPager(mBinding.viewPager, data);
        setupTablayout(data);
    }

    @Override
    public void setUIFooter(Integer qty, Integer total) {
        if (qty > 0) {
            getViewDataBinding().llFooter.setVisibility(View.VISIBLE);
            getViewDataBinding().tvItem.setText("" + qty + " Item in cart");
            getViewDataBinding().tvPrice.setText("\u20b9" + total);
        } else {
            getViewDataBinding().llFooter.setVisibility(View.GONE);
        }
    }

    @Override
    public void setCityToUI(List<String> data) {
        Boolean withinArea = false;
        if (userDistrict != null) {
            for (String d: data) {
                if (d.equalsIgnoreCase(userDistrict)) {
                    withinArea = true;
                }
            }
            if (!withinArea) {
                if (alertServiceArea.isShowing())
                    alertServiceArea.dismiss();
                alertServiceArea.show();
                alertServiceArea.getButton(DialogInterface.BUTTON_NEGATIVE).setVisibility(View.GONE);
            }
        }
    }

    public void setFooterUIFromSubCat() {
        viewModel.getCategoryAndSubCategoriesForFooterOnly();
    }

    private void setupViewPager(ViewPager viewPager, List<SalonServiceModel> data) {


        for (int i = 0; i < data.size(); i++) {
            mPagerAdapter.addFrag(SalonCatFragment.newInstance(i, data.get(i)), data.get(i).getCat());
        }

        viewPager.setAdapter(mPagerAdapter);
    }

    private void setupTablayout(List<SalonServiceModel> mCategory) {
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
        for (int i = 0; i < mCategory.size(); i++) {
            if (i == 0) {
                mBinding.tabLayout.getTabAt(i).setCustomView(createTabItemView(mCategory.get(i), mCategory.get(i).getCat_active_icon()
                        , true
                ));
            } else {
                mBinding.tabLayout.getTabAt(i).setCustomView(createTabItemView(mCategory.get(i), mCategory.get(i).getCat_normal_icon()
                        , false
                ));
            }
        }
    }


    private View createTabItemView(SalonServiceModel mCategory, String imgUri, boolean isActive) {

        LayoutInflater vi = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.category_tab_item, null);

        ImageView imageView = v.findViewById(R.id.imgCat);
        TextView tv = v.findViewById(R.id.tvCatName);
        LinearLayout llLayout = v.findViewById(R.id.llLayout);
        tv.setText(mCategory.getCat());

        if (isActive) {
            v.findViewById(R.id.vOrange).setVisibility(View.VISIBLE);
            tv.setTextColor(getResources().getColor(R.color.black));
            llLayout.setBackground(getResources().getDrawable(R.drawable.circle_tab_selected));

            if (mCategory.getCat().equalsIgnoreCase("All")) {
                Glide.with(this.getActivity()).load(getResources().getDrawable(R.drawable.checkbox_selcted)).into(imageView);
            } else {
                Glide.with(this.getActivity()).load(imgUri).into(imageView);
            }
        } else {
            v.findViewById(R.id.vOrange).setVisibility(View.INVISIBLE);
            tv.setTextColor(getResources().getColor(R.color.dark_gray));
            llLayout.setBackground(getResources().getDrawable(R.drawable.circle_tab_unselected));

            if (mCategory.getCat().equalsIgnoreCase("All")) {
                Glide.with(this.getActivity()).load(getResources().getDrawable(R.drawable.checkbox_unselcted)).into(imageView);
            } else {
                Glide.with(this.getActivity()).load(imgUri).into(imageView);
            }
        }

        v.setTag(mCategory.getCat());
        return v;
    }

    private void changeTabItemView(View v, SalonServiceModel mCategory, String imgUri, boolean isActive) {

        ImageView imageView = v.findViewById(R.id.imgCat);
        TextView tv = v.findViewById(R.id.tvCatName);
        LinearLayout llLayout = v.findViewById(R.id.llLayout);
        tv.setText(mCategory.getCat());

        if (isActive) {
            v.findViewById(R.id.vOrange).setVisibility(View.VISIBLE);
            tv.setTextColor(getResources().getColor(R.color.black));
            llLayout.setBackground(getResources().getDrawable(R.drawable.circle_tab_selected));

            if (mCategory.getCat().equalsIgnoreCase("All")) {
                Glide.with(getActivity()).load(getResources().getDrawable(R.drawable.checkbox_selcted)).into(imageView);
            } else {
                Glide.with(getActivity()).load(imgUri).into(imageView);
            }
        } else {
            v.findViewById(R.id.vOrange).setVisibility(View.INVISIBLE);
            tv.setTextColor(getResources().getColor(R.color.dark_gray));
            llLayout.setBackground(getResources().getDrawable(R.drawable.circle_tab_unselected));

            if (mCategory.getCat().equalsIgnoreCase("All")) {
                Glide.with(getActivity()).load(getResources().getDrawable(R.drawable.checkbox_unselcted)).into(imageView);
            } else {
                Glide.with(getActivity()).load(imgUri).into(imageView);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        locationManager.removeUpdates(locationListener);
        locationManager = null;
        locationListener = null;
    }
}
