package com.littlejoyindia.littlejoyindia.ui.dashboard.profile;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.FragmentEditProfileBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.profileModel.ProfileResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.profileModel.UpdateNameResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class EditProfileFragment extends BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>
        implements EditProfileNavigator {

    public static final String TAG = EditProfileFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private EditProfileViewModel viewModel;

    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_edit_profile;
    }

    @Override
    public EditProfileViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(EditProfileViewModel.class);
        return viewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        viewModel.getProfile();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getViewDataBinding().txtName.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvName.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().txtMobile.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvMobile.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().txtEmail.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvEmail.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().txtReferId.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvReferId.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().txtDate.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvDate.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().btnSave.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_bold.ttf"));
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
    public void onSuccessMyEditProfile(UpdateNameResponse response) {
        if (response.getSuccess()) {
            showToastMessage(response.getMessage());
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                    .add(R.id.clRootView, ProfileFragment.newInstance(), ProfileFragment.TAG)
                    // .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onSaveProfile() {
        if (getViewDataBinding().tvName.getText() != null && !getViewDataBinding().tvName.getText().toString().trim().equals(""))
            viewModel.updateName(getViewDataBinding().tvName.getText().toString().trim());
        else showToastMessage("Enter your name...");
    }
}