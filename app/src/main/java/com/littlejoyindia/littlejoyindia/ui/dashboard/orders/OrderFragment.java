package com.littlejoyindia.littlejoyindia.ui.dashboard.orders;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.FragmentOrderBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.orders.orderModel.OrderResponse;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class OrderFragment extends BaseFragment<FragmentOrderBinding, OrderViewModel>
        implements OrderNavigator {

    public static final String TAG = OrderFragment.class.getSimpleName();
    OrderAdapter adapter;
    OrderResponse orderResponse;
    ArrayList<OrderResponse.Data> dataList = new ArrayList<>();

    @Inject
    ViewModelProviderFactory factory;
    private OrderViewModel viewModel;

    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
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
        return R.layout.fragment_order;
    }

    @Override
    public OrderViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(OrderViewModel.class);
        return viewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        viewModel.myOrders();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getViewDataBinding().tvSuccess.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_bold.ttf"));
        getViewDataBinding().tvRefunded.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvFailed.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));

    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessMyOrders(OrderResponse response) {
        if (response.getData() != null) {
            orderResponse = response;
            for (int i = 0; i < orderResponse.getData().size(); i++) {
                if (orderResponse.getData().get(i).getProduct_details() != null) {
                    for (int j = 0; j < orderResponse.getData().get(i).getProduct_details().size(); j++) {
                        ArrayList<OrderResponse.ProductDetails> detail = new ArrayList<>();
                        detail.add(orderResponse.getData().get(i).getProduct_details().get(j));
                        OrderResponse.Data data = new OrderResponse.Data();
                        data.setPayment_status(orderResponse.getData().get(i).getPayment_status());
                        data.setOrder_id(orderResponse.getData().get(i).getOrder_id());
                        data.setCustomer_phone(orderResponse.getData().get(i).getCustomer_phone());
                        data.setCustomer_email(orderResponse.getData().get(i).getCustomer_email());
                        data.setService_date(orderResponse.getData().get(i).getService_date());
                        data.setService_time(orderResponse.getData().get(i).getService_time());
                        data.setName(orderResponse.getData().get(i).getName());
                        data.setEmail(orderResponse.getData().get(i).getEmail());
                        data.setMobile(orderResponse.getData().get(i).getMobile());
                        data.setAddress(orderResponse.getData().get(i).getAddress());
                        data.setHouse_no(orderResponse.getData().get(i).getHouse_no());
                        data.setAddress_type(orderResponse.getData().get(i).getAddress_type());
                        data.setLandmark(orderResponse.getData().get(i).getLandmark());
                        data.setLatitude(orderResponse.getData().get(i).getLatitude());
                        data.setLongitude(orderResponse.getData().get(i).getLongitude());
                        data.setCity(orderResponse.getData().get(i).getCity());
                        data.setAddress_flag(orderResponse.getData().get(i).getAddress_flag());
                        data.setPaid_on(orderResponse.getData().get(i).getPaid_on());
                        data.setPay_mode(orderResponse.getData().get(i).getPay_mode());
                        data.setPaid_amount(orderResponse.getData().get(i).getPaid_amount());
                        data.setTotal_amount(orderResponse.getData().get(i).getTotal_amount());
                        data.setDiscount_percent(orderResponse.getData().get(i).getDiscount_percent());
                        data.setDiscount_amount(orderResponse.getData().get(i).getDiscount_amount());
                        data.setTax_percent(orderResponse.getData().get(i).getTax_percent());
                        data.setTax_amount(orderResponse.getData().get(i).getTax_amount());
                        data.setTxn_id(orderResponse.getData().get(i).getTxn_id());
                        data.setCanceled(orderResponse.getData().get(i).getCanceled());
                        data.setOrder_status(orderResponse.getData().get(i).getOrder_status());
                        data.setProduct_details(detail);
                        this.dataList.add(data);
                    }
                } else dataList.add(orderResponse.getData().get(i));
            }
            ArrayList<OrderResponse.Data> list = new ArrayList<>();
            for (int i = 0; i < dataList.size(); i++)
                if (dataList.get(i).getPayment_status() == 1)
                    list.add(dataList.get(i));
            setAdapter(1, list);
        } else showToastMessage(response.getMessage());
    }

    @Override
    public void success() {
        getViewDataBinding().tvSuccess.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_bold.ttf"));
        getViewDataBinding().tvRefunded.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvFailed.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvSuccess.setTextColor(getContext().getColor(R.color.white));
        getViewDataBinding().view1.setBackgroundResource(R.color.white);
        getViewDataBinding().tvRefunded.setTextColor(getContext().getColor(R.color.app_grey));
        getViewDataBinding().view2.setBackgroundResource(R.color.transparent);
        getViewDataBinding().tvFailed.setTextColor(getContext().getColor(R.color.app_grey));
        getViewDataBinding().view3.setBackgroundResource(R.color.transparent);

        ArrayList<OrderResponse.Data> list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++)
            if (dataList.get(i).getPayment_status() == 1)
                list.add(dataList.get(i));

        setAdapter(1, list);
    }

    @Override
    public void refunded() {
        getViewDataBinding().tvSuccess.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvRefunded.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_bold.ttf"));
        getViewDataBinding().tvFailed.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvSuccess.setTextColor(getContext().getColor(R.color.app_grey));
        getViewDataBinding().view1.setBackgroundResource(R.color.transparent);
        getViewDataBinding().tvRefunded.setTextColor(getContext().getColor(R.color.white));
        getViewDataBinding().view2.setBackgroundResource(R.color.white);
        getViewDataBinding().tvFailed.setTextColor(getContext().getColor(R.color.app_grey));
        getViewDataBinding().view3.setBackgroundResource(R.color.transparent);

        ArrayList<OrderResponse.Data> list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++)
            if (dataList.get(i).getPayment_status() == 2)
                list.add(dataList.get(i));

        setAdapter(2, list);
    }

    @Override
    public void failed() {
        getViewDataBinding().tvSuccess.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvRefunded.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_regular.ttf"));
        getViewDataBinding().tvFailed.setTypeface(Typeface.createFromAsset(requireContext().getResources().getAssets(), "dosis/dosis_bold.ttf"));
        getViewDataBinding().tvSuccess.setTextColor(getContext().getColor(R.color.app_grey));
        getViewDataBinding().view1.setBackgroundResource(R.color.transparent);
        getViewDataBinding().tvRefunded.setTextColor(getContext().getColor(R.color.app_grey));
        getViewDataBinding().view2.setBackgroundResource(R.color.transparent);
        getViewDataBinding().tvFailed.setTextColor(getContext().getColor(R.color.white));
        getViewDataBinding().view3.setBackgroundResource(R.color.white);

        ArrayList<OrderResponse.Data> list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++)
            if (dataList.get(i).getPayment_status() == 0)
                list.add(dataList.get(i));

        setAdapter(0, list);
    }

    private void setAdapter(Integer statusSelected, ArrayList<OrderResponse.Data> list) {
        adapter = new OrderAdapter(list, statusSelected, data -> {
            ShippingOrderDetailsFragment frag = new ShippingOrderDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("details", new Gson().toJson(data));
            bundle.putInt("status", data.getPayment_status());
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                    .replace(R.id.clRootView, frag.newInstance(bundle), frag.TAG)
                    // .addToBackStack(null)
                    .commit();
        });
        getViewDataBinding().rvOrders.setAdapter(adapter);
    }
}