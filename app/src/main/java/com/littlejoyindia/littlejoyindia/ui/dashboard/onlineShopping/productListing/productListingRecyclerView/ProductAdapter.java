package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.productListingRecyclerView;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.ProductListResponse;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<ProductListResponse.Datum> modelList;
    Context context;
    ProductItemClickListener listener;
    boolean hideDes = false;

    public ProductAdapter(List<ProductListResponse.Datum> modelList, ProductItemClickListener listener, boolean hideDes) {
        this.modelList = modelList;
        this.listener = listener;
        this.hideDes = hideDes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.shopping_product_card,
                        parent, false);
        ViewHolder viewHolder = new ViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.llDes.setOnClickListener((view -> listener.onItemClick(position)));

        holder.titleTV.setText(modelList.get(position).getProductTitle());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.desTV.setText(Html.fromHtml(modelList.get(position).getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.desTV.setText(Html.fromHtml(modelList.get(position).getDescription()));
        }
        holder.priceTV.setText("\u20B9 " + String.valueOf(modelList.get(position).getProductSellingPrice()));
        holder.tvActualPrice.setText("\u20B9 " + String.valueOf(modelList.get(position).getProductActualPrice()));
        holder.tvActualPrice.setPaintFlags(holder.tvActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        if (modelList.get(position).getProductImage() != null) {
            if (!modelList.get(position).getProductImage().isEmpty()) {
                Glide.with(context).load(modelList.get(position).getProductImage().get(0))
                        .into(holder.imageView);
            }
        }

        if (hideDes){
            holder.llDes.setVisibility(View.GONE);
            holder.llPrice.setVisibility(View.VISIBLE);
            holder.imageView.setOnClickListener((view -> listener.onItemClick(position)) );
            holder.shortTVTitle.setText(modelList.get(position).getProductTitle());
            holder.sellingPrice.setText("\u20B9 " + String.valueOf(modelList.get(position).getProductSellingPrice()));
            holder.actualPrice.setPaintFlags(holder.tvActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.actualPrice.setText("\u20B9 " + String.valueOf(modelList.get(position).getProductActualPrice()));
            float per = (modelList.get(position).getProductSellingPrice() * 100) / modelList.get(position).getProductActualPrice();

            holder.tvPer.setText("Save "+String.valueOf(Math.round(per))+" %");

        }else{
            ViewGroup.LayoutParams params =  holder.itemView.getLayoutParams();
            // Changes the height and width to the specified *pixels*
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;

            holder.itemView.setLayoutParams(params);

            holder.imageView.setOnClickListener((view -> listener.onImageClick(position)) );

        }
    }

    @Override
    public int getItemCount() {
        if (modelList != null && modelList.size() > 0) {
            return modelList.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView titleTV;
        public TextView desTV;
        public TextView priceTV;
        public TextView tvActualPrice;
        public TextView shortTVTitle;
        public TextView sellingPrice;
        public TextView actualPrice;
        public TextView tvPer;
        public LinearLayout llDes;
        public LinearLayout llPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.imageProduct);
            this.titleTV = (TextView) itemView.findViewById(R.id.titleTV);
            this.desTV = (TextView) itemView.findViewById(R.id.desTV);
            this.priceTV = (TextView) itemView.findViewById(R.id.priceTV);
            this.tvActualPrice = (TextView) itemView.findViewById(R.id.tvActualPrice);
            this.llDes = (LinearLayout) itemView.findViewById(R.id.llDes);
            this.llPrice = (LinearLayout) itemView.findViewById(R.id.llPrice);
            this.sellingPrice = (TextView) itemView.findViewById(R.id.sellingPrice);
            this.actualPrice = (TextView) itemView.findViewById(R.id.actualPrice);
            this.tvPer = (TextView) itemView.findViewById(R.id.tvPer);
            this.shortTVTitle = (TextView) itemView.findViewById(R.id.shortTVTitle);
        }
    }
}


