package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productCategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.OnlineShoppingResponse;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.OnlineShoppingNavigator;

import java.util.ArrayList;

public class ProductCategoryAdapter extends ArrayAdapter<OnlineShoppingResponse.Datum> {

    OnlineShoppingNavigator onlineShoppingNavigator;

    public ProductCategoryAdapter(@NonNull Context context, ArrayList<OnlineShoppingResponse.Datum> courseModelArrayList, OnlineShoppingNavigator onlineShoppingNavigator) {
        super(context, 0, courseModelArrayList);
        this.onlineShoppingNavigator = onlineShoppingNavigator;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.shopping_category_item, parent, false);
        }

        listitemView.setOnClickListener((view -> {
            onlineShoppingNavigator.onItemClickListener(getItem(position));
        }));
        OnlineShoppingResponse.Datum model = getItem(position);
        TextView tv = listitemView.findViewById(R.id.categoryTitle);
        ImageView imgView = listitemView.findViewById(R.id.imageCategory);
        tv.setText(model.getCategory());
//        courseIV.setImageResource(model.getImgid());
        if (model.getCategoryImage() != null) {
            Glide.with(getContext()).load(model.getCategoryImage()).into(imgView);
        }
        return listitemView;
    }
}
