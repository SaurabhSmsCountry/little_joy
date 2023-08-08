package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productCategory;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.ProductListResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.OnlineShoppingNavigator;

import java.util.ArrayList;

public class HomeProductsAdapter extends ArrayAdapter<ProductListResponse.Datum> {

    OnlineShoppingNavigator onlineShoppingNavigator;

    public HomeProductsAdapter(@NonNull Context context, ArrayList<ProductListResponse.Datum> courseModelArrayList, OnlineShoppingNavigator onlineShoppingNavigator) {
        super(context, 0, courseModelArrayList);
        this.onlineShoppingNavigator = onlineShoppingNavigator;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.shopping_product_item, parent, false);
        }

        listitemView.setOnClickListener((view -> {
            onlineShoppingNavigator.onProductItemClickListener(getItem(position));
        }));
        ProductListResponse.Datum model = getItem(position);
        TextView tv = listitemView.findViewById(R.id.categoryTitle);
        LinearLayout llPrice = listitemView.findViewById(R.id.llPrice);
        llPrice.setVisibility(View.VISIBLE);
        TextView tvActualPrice = listitemView.findViewById(R.id.actualPrice);
        TextView tvSellingPrice = listitemView.findViewById(R.id.sellingPrice);
        TextView tvPer = listitemView.findViewById(R.id.tvPer);
        ImageView imgView = listitemView.findViewById(R.id.imageCategory);
        tv.setText(model.getProductTitle());
        if (model.getProductImage() != null) {
            Glide.with(getContext()).load(model.getProductImage().get(0)).into(imgView);
        }

        String selling = "\u20B9 " + String.valueOf(model.getProductSellingPrice());
        String actual = "\u20B9 " + String.valueOf(model.getProductActualPrice());
        tvSellingPrice.setText(selling);
        tvActualPrice.setText(actual);

        tvActualPrice.setPaintFlags(tvActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        float per = (model.getProductSellingPrice() * 100) / model.getProductActualPrice();

        tvPer.setText("(Save "+String.valueOf(Math.round(per))+" %)");
        return listitemView;
    }
}
