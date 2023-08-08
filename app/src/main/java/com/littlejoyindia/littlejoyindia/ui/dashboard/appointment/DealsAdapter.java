package com.littlejoyindia.littlejoyindia.ui.dashboard.appointment;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.littlejoyindia.littlejoyindia.data.model.Deal;
import com.littlejoyindia.littlejoyindia.databinding.ItemDealsAdapterBinding;
import com.littlejoyindia.littlejoyindia.databinding.ItemEmptyModelBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewHolder;
import com.littlejoyindia.littlejoyindia.utils.EmptyItemViewModel;
import java.util.List;


public class DealsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Deal> modelList;
    private AdapterListener mListener;

    public DealsAdapter(List<Deal> modelList) {
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
            return VIEW_TYPE_NORMAL;
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
                ItemDealsAdapterBinding itemQuizListingBinding = ItemDealsAdapterBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new ListingViewHolder(itemQuizListingBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemEmptyModelBinding emptyViewBinding = ItemEmptyModelBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<Deal> list) {
        modelList.clear();
        modelList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItems() {
        modelList.clear();
    }

    public void setListener(AdapterListener listener) {
        this.mListener = listener;
    }

    public interface AdapterListener {

        void updateCartQty(Deal serviceModel, int i);
    }


    public class ListingViewHolder extends BaseViewHolder implements DealsItemViewModel.ModelListener {

        private ItemDealsAdapterBinding mBinding;

        private DealsItemViewModel itemViewModel;

        public ListingViewHolder(ItemDealsAdapterBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Deal blog = modelList.get(position);
            itemViewModel = new DealsItemViewModel(blog, this);
            mBinding.setViewModel(itemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void addToCart(Deal serviceModel) {
            mListener.updateCartQty(serviceModel, 1);
        }

        @Override
        public void updateCartQty(Deal serviceModel, int qtyToUpdate) {
            mListener.updateCartQty(serviceModel, qtyToUpdate);
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