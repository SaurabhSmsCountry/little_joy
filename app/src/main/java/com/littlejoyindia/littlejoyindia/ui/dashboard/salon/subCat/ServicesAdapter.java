package com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.littlejoyindia.littlejoyindia.databinding.CategoryViewBinding;
import com.littlejoyindia.littlejoyindia.databinding.ServicesViewBinding;
import java.util.List;


public class ServicesAdapter extends ExpandableRecyclerAdapter<Category, Services, CategoryViewHolder, ServicesViewHolder> {


    private List<Category> mSubCategoryList;
    private ServiceAdapterListener mListener;



    public ServicesAdapter( @NonNull List<Category> recipeList) {
        super(recipeList);
        this.mSubCategoryList = recipeList;
    }


    public interface ServiceAdapterListener {
        void addToCartOnServer(Services serviceModel);

        void updateCartQty(Services serviceModel, int qtyToUpdate);
    }

    @UiThread
    @NonNull
    @Override
    public CategoryViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        CategoryViewBinding blogViewBinding = CategoryViewBinding.inflate(LayoutInflater.from(parentViewGroup.getContext()),
                parentViewGroup, false);
        return new CategoryViewHolder(blogViewBinding);
    }

    @UiThread
    @NonNull
    @Override
    public ServicesViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        ServicesViewBinding blogViewBinding = ServicesViewBinding.inflate(LayoutInflater.from(childViewGroup.getContext()),
                childViewGroup, false);
        return new ServicesViewHolder(blogViewBinding, mListener);
    }

    @UiThread
    @Override
    public void onBindParentViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int parentPosition, @NonNull Category recipe) {
        categoryViewHolder.bind(recipe);
    }

    @UiThread
    @Override
    public void onBindChildViewHolder(@NonNull ServicesViewHolder servicesViewHolder, int parentPosition, int childPosition, @NonNull Services ingredient) {
        servicesViewHolder.bind(ingredient);
    }


    public void addItems(List<Category> list) {
        mSubCategoryList.clear();
        mSubCategoryList.addAll(list);
        notifyParentDataSetChanged(true);

    }

    public void clearItems() {
        mSubCategoryList.clear();
    }

    public void setListener(ServiceAdapterListener listener) {
        this.mListener = listener;
    }

}