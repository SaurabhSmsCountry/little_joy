package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.addReview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.modals.MyShoppingOrderListResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing.productListingRecyclerView.ProductItemClickListener;

import java.util.List;


public class AddReviewProductAdapter extends RecyclerView.Adapter<AddReviewProductAdapter.ViewHolder> {

    List<MyShoppingOrderListResponse.ProductDetail> modelList;
    Context context;
    AddProductReviewListener listener;
    boolean hideDes = false;

    public AddReviewProductAdapter(List<MyShoppingOrderListResponse.ProductDetail> modelList, AddProductReviewListener listener) {
        this.modelList = modelList;
        this.listener = listener;
        this.hideDes = hideDes;
    }

    @NonNull
    @Override
    public AddReviewProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.shopping_product_card,
                        parent, false);
        AddReviewProductAdapter.ViewHolder viewHolder = new AddReviewProductAdapter.ViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddReviewProductAdapter.ViewHolder holder, int position) {


        holder.titleTV.setText(modelList.get(position).getService());
        holder.priceTV.setText("\u20B9 " + String.valueOf(modelList.get(position).getPrice()));
        holder.tvActualPrice.setText("");

        if (modelList.get(position).getProductImage() != null) {
            if (!modelList.get(position).getProductImage().isEmpty()) {
                Glide.with(context).load(modelList.get(position).getProductImage().get(0))
                        .into(holder.imageView);
            }
        }

        holder.desTV.setText("");
        holder.llReview.setVisibility(View.VISIBLE);

        holder.btnSubmitReview.setOnClickListener(view -> listener.onItemClick(position, holder.etReview.getText().toString(), Math.round(holder.ratingBar.getRating())));
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
        public LinearLayout llDes;
        public LinearLayout llReview;
        public Button btnSubmitReview;
        public AppCompatRatingBar ratingBar;
        public EditText etReview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.imageProduct);
            this.titleTV = (TextView) itemView.findViewById(R.id.titleTV);
            this.priceTV = (TextView) itemView.findViewById(R.id.priceTV);
            this.tvActualPrice = (TextView) itemView.findViewById(R.id.tvActualPrice);
            this.desTV = (TextView) itemView.findViewById(R.id.desTV);
            this.llReview = (LinearLayout) itemView.findViewById(R.id.llWriteReview);
            this.btnSubmitReview = (Button) itemView.findViewById(R.id.btnSubmitReview);
            this.ratingBar = (AppCompatRatingBar) itemView.findViewById(R.id.productRating);
            this.etReview = (EditText) itemView.findViewById(R.id.etReview);
        }
    }
}



