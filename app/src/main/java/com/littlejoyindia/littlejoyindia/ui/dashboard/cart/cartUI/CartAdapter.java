package com.littlejoyindia.littlejoyindia.ui.dashboard.cart.cartUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;
import com.littlejoyindia.littlejoyindia.databinding.ItemCartBinding;
import com.littlejoyindia.littlejoyindia.databinding.ItemDealsCartBinding;
import com.littlejoyindia.littlejoyindia.databinding.ItemEmptyModelBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewHolder;
import com.littlejoyindia.littlejoyindia.utils.EmptyItemViewModel;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    public static final int VIEW_DEALS_TYPE_NORMAL = 2;

    private List<CartModel> modelList;
    private CartAdapterListener mListener;

    public CartAdapter(List<CartModel> modelList) {
        this.modelList = modelList;
    }

    @Override
    public int getItemCount() {
        if (modelList != null && modelList.size() > 0) {
            return modelList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (modelList != null && !modelList.isEmpty()) {
            if (modelList.get(position).getCartType() == 1){
                return VIEW_TYPE_NORMAL;
            } else {
                return VIEW_DEALS_TYPE_NORMAL;
            }
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemCartBinding itemQuizListingBinding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new ListingViewHolder(itemQuizListingBinding);
            case VIEW_DEALS_TYPE_NORMAL:
                ItemDealsCartBinding itemDealsCartBinding = ItemDealsCartBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new ListingDealsViewHolder(itemDealsCartBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemEmptyModelBinding emptyViewBinding = ItemEmptyModelBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<CartModel> list) {
        modelList.clear();
        modelList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItems() {
        modelList.clear();
    }

    public void setListener(CartAdapterListener listener) {
        this.mListener = listener;
    }

    public interface CartAdapterListener {
        void removeCartItem(CartModel serviceModel);

        void updateCartQty(CartModel serviceModel, int i);
    }



    public class ListingViewHolder extends BaseViewHolder implements CartItemViewModel.ModelListener {

        private ItemCartBinding mBinding;

        private CartItemViewModel itemViewModel;

        public ListingViewHolder(ItemCartBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final CartModel blog = modelList.get(position);
            itemViewModel = new CartItemViewModel(blog, this);
            mBinding.setViewModel(itemViewModel);
            mBinding.executePendingBindings();

        }

        @Override
        public void removeCartItem(CartModel serviceModel) {
            mListener.removeCartItem(serviceModel);
        }

        @Override
        public void updateCartQty(CartModel serviceModel, int i) {
            mListener.updateCartQty(serviceModel, i);
        }
    }

    public class ListingDealsViewHolder extends BaseViewHolder implements DealsCartItemViewModel.ModelListener {

        private ItemDealsCartBinding mBinding;

        private DealsCartItemViewModel itemViewModel;

        public ListingDealsViewHolder(ItemDealsCartBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final CartModel blog = modelList.get(position);
            itemViewModel = new DealsCartItemViewModel(blog, this);
            mBinding.setViewModel(itemViewModel);
            mBinding.executePendingBindings();

        }

        @Override
        public void removeCartItem(CartModel serviceModel) {
            mListener.removeCartItem(serviceModel);
        }

        @Override
        public void updateCartQty(CartModel serviceModel, int i) {
            mListener.updateCartQty(serviceModel, i);
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements EmptyItemViewModel.EmptyItemViewModelListener {

        private ItemEmptyModelBinding mBinding;

        public EmptyViewHolder(ItemEmptyModelBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            EmptyItemViewModel emptyItemViewModel = new EmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

    }
}