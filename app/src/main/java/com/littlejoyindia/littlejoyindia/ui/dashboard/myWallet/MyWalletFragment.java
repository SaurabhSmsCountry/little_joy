package com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet;

import android.annotation.SuppressLint;
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
import com.littlejoyindia.littlejoyindia.databinding.FragmentMyWalletBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet.myWalletModel.MyWalletHistoryResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet.myWalletModel.MyWalletResponse;
import javax.inject.Inject;

public class MyWalletFragment extends BaseFragment<FragmentMyWalletBinding, MyWalletViewModel>
        implements MyWalletNavigator{

    public static final String TAG = MyWalletFragment.class.getSimpleName();
    FragmentMyWalletBinding bind;

    @Inject
    ViewModelProviderFactory factory;
    private MyWalletViewModel viewModel;

    public static MyWalletFragment newInstance() {
        Bundle args = new Bundle();
        MyWalletFragment fragment = new MyWalletFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_wallet;
    }

    @Override
    public MyWalletViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(MyWalletViewModel.class);
        return viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        viewModel.getMyWallet();
        viewModel.getMyWalletHistory();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = getViewDataBinding();
        bind.tvWalletAmount.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtWallet.setTypeface(Typeface.createFromAsset(requireActivity().getAssets(), "dosis/dosis_medium.ttf"));
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSuccessMyWallet(MyWalletResponse response) {
        if (response != null) {
            bind.tvWalletAmount.setText("\u20B9"+response.getData().get(0).getAmount());
        }
    }

    @Override
    public void onSuccessMyWalletHistory(MyWalletHistoryResponse response) {
        if (response != null) {
            WalletAdapter adapter = new WalletAdapter(response.getData());
            bind.rvWallet.setAdapter(adapter);
        }
    }
}