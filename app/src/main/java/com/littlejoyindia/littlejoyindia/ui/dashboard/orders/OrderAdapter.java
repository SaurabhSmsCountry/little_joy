package com.littlejoyindia.littlejoyindia.ui.dashboard.orders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ui.dashboard.orders.orderModel.OrderResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.VH> {

    OnOrderItemClick listener;
    List<OrderResponse.Data> data;
    private Context context;
    Integer status;
    //payment_status = 0 (Failed), 1(Success), 2(Canceled)
    public OrderAdapter(List<OrderResponse.Data> data, Integer status, OnOrderItemClick listener) {
        this.data = data;
        this.status = status;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.tvTitle.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "dosis/dosis_bold.ttf"));
        holder.tvAmount.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "dosis/dosis_semi_bold.ttf"));
        holder.tvDate.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "dosis/dosis_semi_bold.ttf"));
        holder.tvId.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "dosis/dosis_semi_bold.ttf"));
        holder.tvOrderId.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "dosis/dosis_semi_bold.ttf"));
        holder.tvMode.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "dosis/dosis_semi_bold.ttf"));
        holder.tvPaymentMode.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "dosis/dosis_semi_bold.ttf"));
        holder.tvStatus.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "dosis/dosis_semi_bold.ttf"));
        holder.tvOrderStatus.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "dosis/dosis_semi_bold.ttf"));

        holder.tvTitle.setText(data.get(position).getProduct_details().get(0).getService());
        holder.tvId.setText(data.get(position).getOrder_id());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy, hh:mm aa");
        Date date;
        String formattedDate="";
        try {
            date = inputFormat.parse(data.get(position).getPaid_on());
            formattedDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvDate.setText(formattedDate);
        holder.tvStatus.setText(data.get(position).getOrder_status());
        holder.tvAmount.setText("\u20B9"+data.get(position).getProduct_details().get(0).getPrice());
        String mode;
        if (data.get(position).getPay_mode().contains("Wallet")) mode = "Wallet";
        else mode = data.get(position).getPay_mode();
        holder.tvMode.setText(mode);

        if (status == 1) {
            if (data.get(position).getOrder_status() == null || data.get(position).getOrder_status().equals(""))
                holder.tvStatus.setText("Success");
            holder.clBg.setBackgroundResource(R.drawable.success_bg);
            holder.tvStatus.setTextColor(context.getColor(R.color.green_color_theme));
        } else if (status == 2) {
            if (data.get(position).getOrder_status() == null || data.get(position).getOrder_status().equals(""))
                holder.tvStatus.setText("Canceled");
            holder.clBg.setBackgroundResource(R.drawable.processing_bg);
            holder.tvStatus.setTextColor(context.getColor(R.color.golden));
        } else if (status == 0){
            if (data.get(position).getOrder_status() == null || data.get(position).getOrder_status().equals(""))
                holder.tvStatus.setText("Failed");
            holder.clBg.setBackgroundResource(R.drawable.failed_bg);
            holder.tvStatus.setTextColor(context.getColor(R.color.red_color_theme));
        }

        holder.itemView.setOnClickListener(view -> {
            listener.onClick(data.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvId, tvOrderId, tvStatus, tvOrderStatus, tvMode, tvPaymentMode, tvAmount;
        ConstraintLayout clBg;
        public VH(@NonNull View itemView) {
            super(itemView);

            clBg = itemView.findViewById(R.id.clBg);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvId = itemView.findViewById(R.id.tvId);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
            tvMode = itemView.findViewById(R.id.tvMode);
            tvPaymentMode = itemView.findViewById(R.id.tvPaymentMode);
            tvAmount = itemView.findViewById(R.id.tvAmount);
        }
    }
}