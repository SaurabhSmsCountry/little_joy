package com.littlejoyindia.littlejoyindia.ui.dashboard.deals;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.google.gson.Gson;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.FragmentShippingOrderDetailsBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.deals.dealsMode.DealDetailsResponse;
import javax.inject.Inject;

public class ShippingDealsDetailsFragment extends BaseFragment<FragmentShippingOrderDetailsBinding, ShippingDealsDetailsViewModel>
        implements ShippingDealsDetailsNavigator {

    public final String TAG = ShippingDealsDetailsFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    private ShippingDealsDetailsViewModel viewModel;
    FragmentShippingOrderDetailsBinding bind;

    public ShippingDealsDetailsFragment newInstance(Bundle bundle) {
        ShippingDealsDetailsFragment fragment = new ShippingDealsDetailsFragment();
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
    public ShippingDealsDetailsViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(ShippingDealsDetailsViewModel.class);
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

        init();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void init() {
        Gson gson = new Gson();
        if (getArguments() != null) {
            DealDetailsResponse.Data data = gson.fromJson(getArguments().getString("details"), DealDetailsResponse.Data.class);
            int status = getArguments().getInt("status");

            if (status == 1) {
                bind.clBg.setBackgroundResource(R.drawable.success_bg);
                bind.tvAmount.setTextColor(ResourcesCompat.getColor(getResources(), R.color.green_color_theme, null));
            }
            else if (status == 2) {
                bind.clBg.setBackgroundResource(R.drawable.processing_bg);
                bind.tvAmount.setTextColor(ResourcesCompat.getColor(getResources(), R.color.golden, null));
            }
            else if (status == 0) {
                bind.clBg.setBackgroundResource(R.drawable.failed_bg);
                bind.tvAmount.setTextColor(ResourcesCompat.getColor(getResources(), R.color.red_color_theme, null));
            }

            bind.tvOrderNo.setText("Order No : "+data.getOrder_id());
            bind.tvName.setText(data.getName());
            bind.tvEmail.setText(data.getEmail());
            bind.tvContact.setText(data.getMobile());
//            bind.tvAddress.setText(data.getAddress()+", "+data.getHouse_no()+", "+data.getLandmark()+", "+data.getCity()+", "+data.getAddress_type());
//            bind.tvCity.setText(data.getCity());
            /*SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy, hh:mm aa");
            Date date;
            String formattedDate="";
            try {
                date = inputFormat.parse(data.getPaid_on());
                formattedDate = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }*/
//            bind.tvPaidOn.setText(formattedDate);
            bind.tvShipCharge.setText("Charge???");
            bind.tvPayMode.setText(data.getPay_mode());
//            bind.tvTxn.setText(data.getTxn_id());
//            bind.tvTitle.setText(data.getProduct_details().get(0).getService());
//            bind.tvProductCode.setText("Product Code : "+data.getProduct_details().get(0).getSer_id());
            bind.tvAmount.setText("\u20B9"+data.getPaid_amount());
//            bind.tvQty.setText(data.getProduct_details().get(0).getQty());
        }
    }
}