package com.littlejoyindia.littlejoyindia.ui.dashboard.referAndEarn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.FragmentReferAndEarnBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.profileModel.ProfileResponse;

import javax.inject.Inject;

public class ReferAndEarnFragment extends BaseFragment<FragmentReferAndEarnBinding, ReferAndEarnViewModel>
        implements ReferAndEarnNavigator {

    public static final String TAG = ReferAndEarnFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ReferAndEarnViewModel viewModel;

    public static ReferAndEarnFragment newInstance() {
        Bundle args = new Bundle();
        ReferAndEarnFragment fragment = new ReferAndEarnFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_refer_and_earn;
    }

    @Override
    public ReferAndEarnViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(ReferAndEarnViewModel.class);
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

        getViewDataBinding().tvTitle.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_bold.ttf"));
        getViewDataBinding().tvSubTitle.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_bold.ttf"));
        getViewDataBinding().tvTitle1.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_semi_bold.ttf"));
        getViewDataBinding().tvSubTitle1.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvTitle2.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_semi_bold.ttf"));
        getViewDataBinding().tvSubTitle2.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvTitle3.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_semi_bold.ttf"));
        getViewDataBinding().tvSubTitle3.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvReferCode.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_medium.ttf"));
        getViewDataBinding().tvShareNow.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_semi_bold.ttf"));
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSuccessProfile(ProfileResponse response) {
        getViewDataBinding().tvReferCode.setText("Referral Code : "+response.getData().getReferral_code());
    }

    @Override
    public void onShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "link");
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}