package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.cart_item_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.modal.OnlineShoppingCartResponse;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    List<OnlineShoppingCartResponse.Datum> datumList;
    List<CartModel> salonDealDatumList;
    Context context;
    ShoppingCartItemClickListener listener;

    public ShoppingCartAdapter(List<OnlineShoppingCartResponse.Datum> datumList,  List<CartModel> salonDealDatumList, ShoppingCartItemClickListener listener) {
        this.datumList = datumList;
        this.salonDealDatumList = salonDealDatumList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_item,
                        parent, false);
        return new ShoppingCartAdapter.ViewHolder(_view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (datumList != null) {
            OnlineShoppingCartResponse.Datum datum = datumList.get(position);
            holder.tvProductName.setText(datum.getProductName());
            holder.tvCartQty.setText(datum.getQty().toString());
            holder.tvSellingPrice.setText("\u20B9"+datum.getPrice().toString());
        } else if (salonDealDatumList != null) {
            CartModel datum = salonDealDatumList.get(position);
            holder.tvProductName.setText(datum.getDeal_name());
            if (datum.getServices() != null)
            holder.tvProductName.setText(datum.getServices());
            holder.tvCartQty.setText(String.valueOf(datum.getQty()));
            holder.tvSellingPrice.setText("\u20B9"+datum.getPrice());
        }
        holder.minusCartItem.setOnClickListener((view -> listener.onItemMinusClickListener(position)));
        holder.plusCartItem.setOnClickListener((view -> listener.onItemAddClickListener(position)));
        holder.removeCartItem.setOnClickListener((view -> listener.onItemDeleteClickListener(position)));

//         holder.tvRealPrice.setText("\u20B9"+datum.get().toString());
//        holder.tvDescription.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        if (datumList != null && datumList.size() > 0) {
            return datumList.size();
        } else if (salonDealDatumList != null && salonDealDatumList.size() > 0) {
            return salonDealDatumList.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvProductName;
        public ImageButton minusCartItem;
        public ImageButton plusCartItem;
        public ImageButton removeCartItem;
        public TextView tvCartQty;
        public TextView tvDescription;
        public TextView tvSellingPrice;
        public TextView tvRealPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            this.minusCartItem = (ImageButton) itemView.findViewById(R.id.minusCartItem);
            this.plusCartItem = (ImageButton) itemView.findViewById(R.id.plusCartItem);
            this.removeCartItem = (ImageButton) itemView.findViewById(R.id.removeCartItem);
            this.tvCartQty = (TextView) itemView.findViewById(R.id.tvQty);
            this.tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            this.tvSellingPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            this.tvRealPrice = (TextView) itemView.findViewById(R.id.tvRealPrice);
        }
    }
}
