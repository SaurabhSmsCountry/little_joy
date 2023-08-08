package com.littlejoyindia.littlejoyindia.ui.dashboard.profile;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.view.View;
import android.widget.Toast;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.FragmentProfileBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.profileModel.ProfileResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding, ProfileViewModel>
        implements ProfileNavigator {

    public static final String TAG = ProfileFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ProfileViewModel viewModel;

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public ProfileViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(ProfileViewModel.class);
        return viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        viewModel.getProfile();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getViewDataBinding().tvName.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_bold.ttf"));
        getViewDataBinding().txtMobile.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvMobile.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().txtEmail.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvEmail.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().txtReferId.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvReferId.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().txtDate.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvDate.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().btnEdit.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_bold.ttf"));
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessProfile(ProfileResponse response) {
        if (response != null) {
            getViewDataBinding().tvName.setText(response.getData().getFull_name());
            getViewDataBinding().tvMobile.setText(response.getData().getPhone_no());
            getViewDataBinding().tvEmail.setText(response.getData().getEmail_id());
            getViewDataBinding().tvReferId.setText(response.getData().getReferral_code());
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-mm-dd, hh:mm:ss");
            Date date = null;
            String formattedDate="";
            try {
                date = inputFormat.parse(response.getData().getDate());
                formattedDate = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            getViewDataBinding().tvDate.setText(formattedDate);
        }
    }

    @Override
    public void onEditProfile() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .add(R.id.clRootView, EditProfileFragment.newInstance(), EditProfileFragment.TAG)
                // .addToBackStack(null)
                .commit();
    }
}