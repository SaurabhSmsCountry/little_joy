package com.littlejoyindia.littlejoyindia.ui.dashboard.deals;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.FragmentMyDealsBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.deals.dealsMode.DealDetailsResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.deals.dealsMode.DealsResponse;

import java.util.ArrayList;

import javax.inject.Inject;

public class MyDealsFragment extends BaseFragment<FragmentMyDealsBinding, DealsViewModel>
        implements DealsNavigator {

    public static final String TAG = MyDealsFragment.class.getSimpleName();
    DealsAdapter adapter;
    DealsResponse dealsResponse;

    @Inject
    ViewModelProviderFactory factory;
    private DealsViewModel viewModel;

    public static MyDealsFragment newInstance() {
        MyDealsFragment fragment = new MyDealsFragment();
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
        return R.layout.fragment_my_deals;
    }

    @Override
    public DealsViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(DealsViewModel.class);
        return viewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        viewModel.getMyDeals();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewDataBinding().tvSuccess.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_bold.ttf"));
        getViewDataBinding().tvCancelled.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvFailed.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessMyDeals(DealsResponse response) {
        if (response.getData() != null) {
            dealsResponse = response;
            ArrayList<DealsResponse.Data> list = new ArrayList<>();
            for (int i = 0; i < dealsResponse.getData().size(); i++)
                if (dealsResponse.getData().get(i).getPayment_status() == 1)
                    list.add(dealsResponse.getData().get(i));
            setAdapter(1, list);
        } else showToastMessage(response.getMessage());
    }

    @Override
    public void onSuccessMyDealDetails(DealDetailsResponse response) {
        ShippingDealsDetailsFragment frag = new ShippingDealsDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("details", new Gson().toJson(response.getData().get(0)));
        bundle.putInt("status", response.getData().get(0).getPayment_status());
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.clRootView, frag.newInstance(bundle), frag.TAG)
                // .addToBackStack(null)
                .commit();
    }

    @Override
    public void success() {
        getViewDataBinding().tvSuccess.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_bold.ttf"));
        getViewDataBinding().tvCancelled.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvFailed.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvSuccess.setTextColor(getContext().getColor(R.color.white));
        getViewDataBinding().view1.setBackgroundResource(R.color.white);
        getViewDataBinding().tvCancelled.setTextColor(getContext().getColor(R.color.app_grey));
        getViewDataBinding().view2.setBackgroundResource(R.color.transparent);
        getViewDataBinding().tvFailed.setTextColor(getContext().getColor(R.color.app_grey));
        getViewDataBinding().view3.setBackgroundResource(R.color.transparent);

        ArrayList<DealsResponse.Data> list = new ArrayList<>();
        for (int i = 0; i < dealsResponse.getData().size(); i++)
            if (dealsResponse.getData().get(i).getPayment_status() == 1)
                list.add(dealsResponse.getData().get(i));

        setAdapter(1, list);
    }

    @Override
    public void cancelled() {
        getViewDataBinding().tvSuccess.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvCancelled.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_bold.ttf"));
        getViewDataBinding().tvFailed.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvSuccess.setTextColor(getContext().getColor(R.color.app_grey));
        getViewDataBinding().view1.setBackgroundResource(R.color.transparent);
        getViewDataBinding().tvCancelled.setTextColor(getContext().getColor(R.color.white));
        getViewDataBinding().view2.setBackgroundResource(R.color.white);
        getViewDataBinding().tvFailed.setTextColor(getContext().getColor(R.color.app_grey));
        getViewDataBinding().view3.setBackgroundResource(R.color.transparent);

        ArrayList<DealsResponse.Data> list = new ArrayList<>();
        for (int i = 0; i < dealsResponse.getData().size(); i++)
            if (dealsResponse.getData().get(i).getPayment_status() == 2)
                list.add(dealsResponse.getData().get(i));

        setAdapter(2, list);
    }

    @Override
    public void failed() {
        getViewDataBinding().tvSuccess.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvCancelled.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvFailed.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_bold.ttf"));
        getViewDataBinding().tvSuccess.setTextColor(getContext().getColor(R.color.app_grey));
        getViewDataBinding().view1.setBackgroundResource(R.color.transparent);
        getViewDataBinding().tvCancelled.setTextColor(getContext().getColor(R.color.app_grey));
        getViewDataBinding().view2.setBackgroundResource(R.color.transparent);
        getViewDataBinding().tvFailed.setTextColor(getContext().getColor(R.color.white));
        getViewDataBinding().view3.setBackgroundResource(R.color.white);

        ArrayList<DealsResponse.Data> list = new ArrayList<>();
        for (int i = 0; i < dealsResponse.getData().size(); i++)
            if (dealsResponse.getData().get(i).getPayment_status() == 0)
                list.add(dealsResponse.getData().get(i));

        setAdapter(0, list);
    }

    private void setAdapter(Integer statusSelected, ArrayList<DealsResponse.Data> list) {
        adapter = new DealsAdapter(list, statusSelected, data -> {
            viewModel.getMyDealDetails(data.getOrder_id());
        });
        getViewDataBinding().rvDeals.setAdapter(adapter);
    }
}