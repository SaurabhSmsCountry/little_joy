package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderConfirm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.Address;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonRequest;
import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonResponse;
import com.littlejoyindia.littlejoyindia.databinding.ActivityOnlineShoppingOrderConfirmationBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.cart_item_list.ShoppingCartAdapter;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.cart_item_list.ShoppingCartItemClickListener;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.modal.OnlineShoppingCartResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.PaymentActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class ShoppingOrderConfirmationScreen extends BaseActivity<ActivityOnlineShoppingOrderConfirmationBinding, ShoppingOrderConfirmationViewModal> implements ShoppingOrderConfirmationNavigator {

    private ActivityOnlineShoppingOrderConfirmationBinding binding;

    public static final String TAG = ShoppingOrderConfirmationScreen.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private ShoppingOrderConfirmationViewModal viewModel;

    Address address = null;
    String userName = null;
    String userMobile = null, dateOfService = null, timeOfService = null, userEmail = null, city = null;

    int checkoutType = 0;
    int cartId = -1;
    int cartTotal = 0;
    int actualCartTotal = 0;
    boolean isCodAvailable = false;

    RecyclerView recyclerView;
    ShoppingCartAdapter mAdapter;
    List<OnlineShoppingCartResponse.Datum> datumList = new ArrayList<>();
    List<CartModel> salonDealDatumList = new ArrayList<>();


    public static Intent newIntent(Context context) {
        return new Intent(context, ShoppingOrderConfirmationScreen.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_online_shopping_order_confirmation;
    }

    @Override
    public ShoppingOrderConfirmationViewModal getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(ShoppingOrderConfirmationViewModal.class);
        return viewModel;
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUI(OnlineShoppingCartResponse response) {
        datumList.clear();
        datumList.addAll(response.getData());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new ShoppingCartAdapter(datumList, null, new ShoppingCartItemClickListener() {
            @Override
            public void onItemClickListener(int position) {

            }

            @Override
            public void onItemDeleteClickListener(int position) {
                SalonRequest.RemoveDealCartItemRequest request = new SalonRequest.RemoveDealCartItemRequest(datumList.get(position).getCartId().toString(), datumList.get(position).getProId());
                viewModel.removeItemFromCart(request);
            }

            @Override
            public void onItemMinusClickListener(int position) {
                OnlineShoppingCartResponse.Datum datum = datumList.get(position);
                int newQty = -1;
                if (datum.getQty() == 1) {
                    newQty = 0;
                }
                addToAndRemoveFromCart(newQty, datum.getProId(), null);
            }

            @Override
            public void onItemAddClickListener(int position) {
                OnlineShoppingCartResponse.Datum datum = datumList.get(position);
                int newQty = 1;
                addToAndRemoveFromCart(newQty, datum.getProId(), null);
            }
        });
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        cartTotal = response.getTotal();
        actualCartTotal = response.getTotal();

        getViewDataBinding().tvTotalConfirmCost.setText("Your Cart Item Total Cost is Rs. " + cartTotal);

        if (cartTotal == 0) {
            getViewDataBinding().btnConfirmOrder.setText("Back to dashboard");
        }
    }

    @Override
    public void updateSalonDealUI(SalonResponse.CartListingServicesResponse response) {
        salonDealDatumList.clear();
        salonDealDatumList.addAll(response.getData());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new ShoppingCartAdapter(null, salonDealDatumList, new ShoppingCartItemClickListener() {
            @Override
            public void onItemClickListener(int position) {

            }

            @Override
            public void onItemDeleteClickListener(int position) {
                if (checkoutType == 0) {
                    SalonRequest.RemoveDealCartItemRequest req2 = new SalonRequest.RemoveDealCartItemRequest(
                            salonDealDatumList.get(position).getCartId().toString(), salonDealDatumList.get(position).getPro_id()
                    );
                    viewModel.removeItemFromCartDeal(req2);
                } else {
                    SalonRequest.RemoveCartItemServicesRequest req = new SalonRequest.RemoveCartItemServicesRequest(
                            salonDealDatumList.get(position).getCartId().toString(), salonDealDatumList.get(position).getSerId()
                    );
                    viewModel.removeItemFromCartSalon(req);
                }
            }

            @Override
            public void onItemMinusClickListener(int position) {
                CartModel datum = salonDealDatumList.get(position);
                int newQty = -1;
                if (datum.getQty() == 1) {
                    newQty = 0;
                }
                addToAndRemoveFromCart(newQty, datum.getPro_id(), datum.getSerId());
            }

            @Override
            public void onItemAddClickListener(int position) {
                CartModel datum = salonDealDatumList.get(position);
                int newQty = 1;
                addToAndRemoveFromCart(newQty, datum.getPro_id(), datum.getSerId());
            }
        });
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        cartTotal = response.getTotal();
        actualCartTotal = response.getTotal();

        getViewDataBinding().tvTotalConfirmCost.setText("Your Cart Item Total Cost is Rs. " + cartTotal);

        if (cartTotal == 0) {
            getViewDataBinding().btnConfirmOrder.setText("Back to dashboard");
        }
    }

    @Override
    public void updateCouponCodeStatus(CouponCodeCheckoutResponse response) {
        if (response.getData() != null) {
            boolean isActive = response.getData().get(0).getIsActive() == 1;
            if (isActive) {
                getViewDataBinding().tvCouponCodeStatus.setText("Coupon Code Applied Successfully");
                getViewDataBinding().tvCouponCodeStatus.setTextColor(getColor(R.color.green_color_theme));
                float discountedPrice;
                if (response.getData().get(0).getDiscountPercent() == 0){
                    discountedPrice = actualCartTotal - response.getData().get(0).getDiscountAmount();
                }else{
                    discountedPrice = actualCartTotal - (actualCartTotal * response.getData().get(0).getDiscountPercent()) / 100;
                }
                cartTotal = Math.round(discountedPrice);
                getViewDataBinding().tvTotalConfirmCost.setText("Your Cart Item Total Cost is Rs. " + String.valueOf(cartTotal));
                getViewDataBinding().tvTotalPrice.setText("Rs. " + String.valueOf(cartTotal).toString());
            } else {
                getViewDataBinding().tvCouponCodeStatus.setText("Entered Coupon expired");
                getViewDataBinding().tvCouponCodeStatus.setTextColor(getColor(R.color.red_color_theme));
            }
        } else {
            getViewDataBinding().tvCouponCodeStatus.setText("Invalid Coupon");
            getViewDataBinding().tvCouponCodeStatus.setTextColor(getColor(R.color.red_color_theme));
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle mBundle = getIntent().getExtras();

        viewModel.setNavigator(this);

        if (mBundle != null) {
            address = (Address) mBundle.getSerializable("address");
            userName = mBundle.getString("userName");
            userMobile = mBundle.getString("userMobile");
            dateOfService = mBundle.getString("dateOfService");
            timeOfService = mBundle.getString("timeOfService");
            userEmail = mBundle.getString("userEmail");

            checkoutType = mBundle.getInt("checkoutType");
            cartId = mBundle.getInt("cartId");
            cartTotal = mBundle.getInt("cartTotal");
            isCodAvailable = mBundle.getBoolean("isCodAvailable");
        }

        getViewDataBinding().imageBack.setOnClickListener(view -> {
            onBackPressed();
        });

        if (address == null)
            getViewDataBinding().llAddress.setVisibility(View.GONE);
        else
            getViewDataBinding().tvOrderConAddress.setText(address.getHouseNo()+" "+address.getCompleteAddress() + "\n"+address.getLandmark()+"\n"+address.getCity() +", "+address.getState()+" "+address.getPinCode());

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:MM");
        String formattedDate = null;
        formattedDate = outputFormat.format(Calendar.getInstance().getTime());

//        holder.tvOrderTime.setText(formattedDate);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        getViewDataBinding().tvOrderConUserDetails.setText(userName +"\n"+ " M.No." + userMobile);

        getViewDataBinding().tvTotalConfirmCost.setText("Your Cart Item Total Cost is Rs. " + cartTotal);
        getViewDataBinding().tvTotalPrice.setText("Rs. " + String.valueOf(cartTotal).toString());

        getViewDataBinding().btnConfirmOrder.setOnClickListener(view -> {
            if (cartTotal == 0) {
                redirectToHomeScreen();
            } else {
                //online shopping
                Intent mIntent = PaymentActivity.newIntent(this);
                mIntent.putExtra("address", address);
                mIntent.putExtra("userName", userName);
                mIntent.putExtra("userMobile", userMobile);
                mIntent.putExtra("userEmail", userEmail);
                mIntent.putExtra("checkoutType", checkoutType);
                mIntent.putExtra("cartId", cartId);
                mIntent.putExtra("cartTotal", cartTotal);
                mIntent.putExtra("isCodAvailable", isCodAvailable);
                mIntent.putExtra("dateOfService",dateOfService);
                mIntent.putExtra("timeOfService",timeOfService);
                mIntent.putExtra("city",city);
                mIntent.putExtra("coupon", getViewDataBinding().etCouponCode.getText().toString().trim());
                startActivity(mIntent);
            }
        });

        recyclerView = getViewDataBinding().cartRecyclerView;

        if (checkoutType == 2) {
            //online shopping
            getViewModel().getCart();
        } else if (checkoutType == 0) {
            //deals
            getViewModel().getDealCart();
        }
        else {
            //salon
            getViewModel().getSalonCart();
        }

        getViewDataBinding().btnCouponApply.setOnClickListener(view -> {
            String code = getViewDataBinding().etCouponCode.getText().toString().trim();
            viewModel.checkCoupon(code);
        });
    }

    private void addToAndRemoveFromCart(int newQty, String prodId, String serId) {
        CartModel model = new CartModel();
        model.setPro_id(prodId);
        model.setQty(newQty);
        model.setSerId(serId);
        if (checkoutType == 2)
            viewModel.addToAndRemoveCart(model);
        else if (checkoutType == 0) {
            //deal
            viewModel.addToAndRemoveCartDeal(model);
        } else {
            //salon
            viewModel.addToAndRemoveCartSalon(model);
        }
    }

    private void redirectToHomeScreen() {
        Intent mIntent = DashboardActivity.newIntent(this);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (cartTotal == 0) {
            redirectToHomeScreen();
        } else {
            super.onBackPressed();
        }
    }
}
