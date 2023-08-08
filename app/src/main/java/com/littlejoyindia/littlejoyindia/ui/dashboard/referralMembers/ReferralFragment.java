package com.littlejoyindia.littlejoyindia.ui.dashboard.referralMembers;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.widget.Toast;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.FragmentReferralBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.referralMembers.referralModel.ReferralMemberResponse;
import javax.inject.Inject;

public class ReferralFragment extends BaseFragment<FragmentReferralBinding, ReferralViewModel>
        implements  ReferralNavigator {

    public static final String TAG = ReferralFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ReferralViewModel viewModel;
    private FragmentReferralBinding mBinding;

    public static ReferralFragment newInstance() {
        Bundle args = new Bundle();
        ReferralFragment fragment = new ReferralFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_referral;
    }

    @Override
    public ReferralViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(ReferralViewModel.class);
        return viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        mBinding = getViewDataBinding();

        viewModel.getReferralMembers();
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessReferralMembers(ReferralMemberResponse response) {
        ReferralAdapter adapter = new ReferralAdapter(response.getData());
        if (response.getData().size() == 0)
            showToastMessage(response.getMessage());
        else
            mBinding.rvMemberList.setAdapter(adapter);
    }
}