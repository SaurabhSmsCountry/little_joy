package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productCategory;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.data.model.onlineShopping.OnlineShoppingResponse;
import com.littlejoyindia.littlejoyindia.databinding.FragmentShoppingSubListDialogListDialogBinding;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.OnlineShoppingNavigator;

import java.util.ArrayList;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ShoppingSubListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ShoppingSubListDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";
    private FragmentShoppingSubListDialogListDialogBinding binding;

    static OnlineShoppingResponse.Datum dataList;
    static OnlineShoppingNavigator onlineShoppingNavigator;

    // TODO: Customize parameters
    public static ShoppingSubListDialogFragment newInstance(OnlineShoppingNavigator onlineShopping, OnlineShoppingResponse.Datum subCategoryList) {
        final ShoppingSubListDialogFragment fragment = new ShoppingSubListDialogFragment();
        /*final Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);*/
        onlineShoppingNavigator = onlineShopping;
        dataList = subCategoryList;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentShoppingSubListDialogListDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final GridView recyclerView = (GridView) binding.list;
        TextView tv = binding.titleSubCategory;

        tv.setText(dataList.getCategory());
        ArrayList<OnlineShoppingResponse.SubCategory> courseModelArrayList = new ArrayList<>(dataList.getSubCategory());
        subCategoryAdapter adapter = new subCategoryAdapter(getActivity().getApplicationContext(), courseModelArrayList, onlineShoppingNavigator, dataList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class subCategoryAdapter extends ArrayAdapter<OnlineShoppingResponse.SubCategory> {

        OnlineShoppingNavigator onlineShoppingNavigator;
        OnlineShoppingResponse.Datum data;

        public subCategoryAdapter(@NonNull Context context, ArrayList<OnlineShoppingResponse.SubCategory> courseModelArrayList, OnlineShoppingNavigator onlineShoppingNavigator, OnlineShoppingResponse.Datum data) {
            super(context, 0, courseModelArrayList);
            this.onlineShoppingNavigator = onlineShoppingNavigator;
            this.data = data;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listitemView = convertView;
            if (listitemView == null) {
                // Layout Inflater inflates each item to be displayed in GridView.
                listitemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_shopping_sub_list_dialog_list_dialog_item, parent, false);
            }

            listitemView.setOnClickListener((view -> {
                onlineShoppingNavigator.onItemSubCategoryClickListener(getItem(position), data);
            }));
            OnlineShoppingResponse.SubCategory model = getItem(position);
            TextView tv = listitemView.findViewById(R.id.categoryTitle);
            ImageView imgView = listitemView.findViewById(R.id.imageCategory);
            tv.setText(model.getCategory());
//        courseIV.setImageResource(model.getImgid());
            if (model.getSubCategoryImage() != null) {
                Glide.with(getContext()).load(model.getSubCategoryImage()).into(imgView);
            }
            return listitemView;

        }
    }
}