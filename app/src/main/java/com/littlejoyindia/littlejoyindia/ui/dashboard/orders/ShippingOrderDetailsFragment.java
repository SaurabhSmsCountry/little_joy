package com.littlejoyindia.littlejoyindia.ui.dashboard.orders;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.FragmentShippingOrderDetailsBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.orders.orderModel.OrderResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class ShippingOrderDetailsFragment extends BaseFragment<FragmentShippingOrderDetailsBinding, ShippingOrderDetailsViewModel>
        implements ShippingOrderDetailsNavigator {

    public final String TAG = ShippingOrderDetailsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ShippingOrderDetailsViewModel viewModel;
    FragmentShippingOrderDetailsBinding bind;

    public ShippingOrderDetailsFragment newInstance(Bundle bundle) {
        ShippingOrderDetailsFragment fragment = new ShippingOrderDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shipping_order_details;
    }

    @Override
    public ShippingOrderDetailsViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(ShippingOrderDetailsViewModel.class);
        return viewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = getViewDataBinding();

        bind.txtShippingTo.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_bold.ttf"));
        bind.tvOrderNo.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_bold.ttf"));
        bind.txtName.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvName.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtEmail.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvEmail.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtContact.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvContact.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtAddress.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvAddress.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtCity.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvCity.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtPaidOn.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvPaidOn.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtShipCharge.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvShipCharge.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtPayMode.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvPayMode.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtTxn.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvTxn.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtProductDetails.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_bold.ttf"));
        bind.tvTitle.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_bold.ttf"));
        bind.tvProductCode.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvAmount.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtQty.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvQty.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtDate.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvDate.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.txtTime.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));
        bind.tvTime.setTypeface(Typeface.createFromAsset(requireContext().getAssets(), "dosis/dosis_semi_bold.ttf"));

        init();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void init() {
        bind.serviceTime.setVisibility(View.VISIBLE);
        bind.serviceDate.setVisibility(View.VISIBLE);
        Gson gson = new Gson();
        if (getArguments() != null) {
            OrderResponse.Data data = gson.fromJson(getArguments().getString("details"), OrderResponse.Data.class);
            int status = getArguments().getInt("status");

            if (status == 1) {
                bind.clBg.setBackgroundResource(R.drawable.success_bg);
                bind.tvAmount.setTextColor(getActivity().getColor(R.color.green_color_theme));
            }
            else if (status == 2) {
                bind.clBg.setBackgroundResource(R.drawable.processing_bg);
                bind.tvAmount.setTextColor(getActivity().getColor(R.color.golden));
            }
            else if (status == 0) {
                bind.clBg.setBackgroundResource(R.drawable.failed_bg);
                bind.tvAmount.setTextColor(getActivity().getColor(R.color.red_color_theme));
            }

            bind.tvOrderNo.setText("Order No : "+data.getOrder_id());
            bind.tvName.setText(data.getName());
            bind.tvEmail.setText(data.getCustomer_email());
            bind.tvContact.setText(data.getCustomer_phone());
            if (data.getAddress() != null)
                bind.llAddress.setVisibility(View.VISIBLE);
            bind.tvAddress.setText(data.getAddress()+", "+data.getHouse_no()+", "+data.getLandmark()+", "+data.getCity()+", "+data.getAddress_type());
            if (data.getCity() != null) {
                bind.txtCity.setVisibility(View.VISIBLE);
                bind.tvCity.setVisibility(View.VISIBLE);
            }
            bind.tvCity.setText(data.getCity());
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy, hh:mm aa");
            Date date;
            String formattedDate="";
            try {
                date = inputFormat.parse(data.getPaid_on());
                formattedDate = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (data.getPaid_on() != null) {
                bind.txtPaidOn.setVisibility(View.VISIBLE);
                bind.tvPaidOn.setVisibility(View.VISIBLE);
            }
            bind.tvPaidOn.setText(formattedDate);
            bind.tvShipCharge.setText("Charge???");
            bind.tvPayMode.setText(data.getPay_mode());
            if (data.getTxn_id() != null) {
                bind.txtTxn.setVisibility(View.VISIBLE);
                bind.tvTxn.setVisibility(View.VISIBLE);
            }
            bind.tvTxn.setText(data.getTxn_id());
            if (data.getProduct_details().get(0).getService() != null)
                bind.tvTitle.setVisibility(View.VISIBLE);
            bind.tvTitle.setText(data.getProduct_details().get(0).getService());
            if (data.getProduct_details().get(0).getSer_id() != null)
                bind.tvProductCode.setVisibility(View.VISIBLE);
            bind.tvProductCode.setText("Product Code : "+data.getProduct_details().get(0).getSer_id());
            bind.tvAmount.setText("\u20B9"+data.getProduct_details().get(0).getPrice());
            if (data.getProduct_details().get(0).getQty() != null) {
                bind.txtQty.setVisibility(View.VISIBLE);
                bind.tvQty.setVisibility(View.VISIBLE);
            }
            bind.tvQty.setText(data.getProduct_details().get(0).getQty());
            bind.tvDate.setText(data.getService_date());
            bind.tvTime.setText(data.getService_time());
        }
    }
}