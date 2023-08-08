package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productReview.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productReview.models.ProductReviewResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProductReviewListAdapter extends RecyclerView.Adapter<ProductReviewListAdapter.ViewHolder> {

    List<ProductReviewResponse.Datum> list;

    public ProductReviewListAdapter(List<ProductReviewResponse.Datum> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ProductReviewListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the layout
        View _view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_review_item,
                viewGroup, false);
        return new ProductReviewListAdapter.ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductReviewListAdapter.ViewHolder viewHolder, int i) {

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd / MM / yyyy");

        Date date = null;
        try {
            date = inputFormat.parse(list.get(i).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);

        viewHolder.reviewerName.setText("Reviewer Name");
        viewHolder.reviewDate.setText(formattedDate);
        viewHolder.reviewComment.setText(list.get(i).getComment());
        viewHolder.ratingBar.setRating(Float.parseFloat(list.get(i).getRating()));
//        viewHolder.ratingBar.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView reviewerName;
        TextView reviewDate;
        TextView reviewComment;
        AppCompatRatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            reviewerName = itemView.findViewById(R.id.reviewerName);
            reviewDate = itemView.findViewById(R.id.reviewDate);
            reviewComment = itemView.findViewById(R.id.reviewComment);
            ratingBar = itemView.findViewById(R.id.reviewRatings);
        }
    }
}
