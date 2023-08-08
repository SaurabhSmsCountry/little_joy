package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.orderList;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.modals.MyShoppingOrderListResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.timeline.TimeLineAdapter;
import com.littlejoyindia.littlejoyindia.utils.TopSliderAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;
import ss.com.bannerslider.Slider;

public class ShoppingOrderListAdapter extends RecyclerView.Adapter<ShoppingOrderListAdapter.ViewHolder> {
    List<MyShoppingOrderListResponse.ShoppingOrderData> data;
    ShoppingOrderItemClickListener listener;
    Context context;

    private List<String> orderStatus = new ArrayList<>();
    TimeLineAdapter mAdapter;
    Slider slider;

    public ShoppingOrderListAdapter(List<MyShoppingOrderListResponse.ShoppingOrderData> data, ShoppingOrderItemClickListener listener, Context context) {
        this.data = data;
        this.listener = listener;
        this.context = context;

        orderStatus.add("Processing");
        orderStatus.add("Accepted");
        orderStatus.add("Shipped");
        orderStatus.add("Delivered");
        orderStatus.add("Completed");
        orderStatus.add("Canceled");
    }

    @NonNull
    @Override
    public ShoppingOrderListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_order_list_item,
                parent, false);
        return new ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingOrderListAdapter.ViewHolder holder, int position) {
        MyShoppingOrderListResponse.ShoppingOrderData datum = data.get(position);
        holder.tvOrderStatus.setText(datum.getOrderStatus());
        holder.tvOrderAddress.setText(datum.getAddress());
        holder.tvPaymentMode.setText(datum.getPayMode());


        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(datum.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);
        holder.tvOrderTime.setText(formattedDate);
        SpannableStringBuilder productDescription = new SpannableStringBuilder(
                "Ordered Product is ");

        for (int i = 0; i < datum.getProductDetails().size(); i++) {
            productDescription.append(datum.getProductDetails().get(i).getService());
            productDescription.append(",");
        }
        holder.tvProductDescription.setText(productDescription);
        holder.itemView.setOnClickListener(view -> listener.onItemClickListener(position));

        mAdapter = new TimeLineAdapter(orderStatus, datum.getOrderStatus(), datum.getUpdationDate(), 1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        holder.recyclerView.setAdapter(mAdapter);
        holder.recyclerView.setHasFixedSize(true);

        // Slider images
        ArrayList<String> sliderArrayList = new ArrayList<>();

        for (int i = 0; i < datum.getProductDetails().size(); i++) {
            sliderArrayList.addAll(datum.getProductDetails().get(i).getProductImage());
        }

        holder.slider.setAdapter(new TopSliderAdapter(context, sliderArrayList));
        CircleIndicator indicator = holder.indicatorTopSlider;
        indicator.createIndicators(sliderArrayList.size() - 1, 0);
    }

    @Override
    public int getItemCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvOrderStatus;
        public TextView tvOrderAddress;
        public TextView tvProductDescription;
        public TextView tvPaymentMode;
        public TextView tvOrderTime;
        public RecyclerView recyclerView;
        public Slider slider;
        public CircleIndicator indicatorTopSlider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvOrderStatus = (TextView) itemView.findViewById(R.id.tvOrderStatus);
            this.tvOrderAddress = (TextView) itemView.findViewById(R.id.tvOrderAddress);
            this.tvProductDescription = (TextView) itemView.findViewById(R.id.tvProductDescription);
            this.tvPaymentMode = (TextView) itemView.findViewById(R.id.tvPaymentMode);
            this.tvOrderTime = (TextView) itemView.findViewById(R.id.tvOrderTime);
            this.recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
            this.slider = (Slider) itemView.findViewById(R.id.topSlider);
            this.indicatorTopSlider = (CircleIndicator) itemView.findViewById(R.id.indicatorTopSlider);
        }
    }
}
