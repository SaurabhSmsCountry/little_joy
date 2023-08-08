package com.littlejoyindia.littlejoyindia.ui.dashboard.addressSelection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.littlejoyindia.littlejoyindia.data.model.Address;
import com.littlejoyindia.littlejoyindia.databinding.ItemAddressBinding;
import com.littlejoyindia.littlejoyindia.databinding.ItemEmptyModelBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewHolder;
import com.littlejoyindia.littlejoyindia.utils.EmptyItemViewModel;

import java.util.List;


public class AddressAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Address> modelList;
    private AdapterListener mListener;

    public AddressAdapter(List<Address> modelList) {
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
                ItemAddressBinding itemQuizListingBinding = ItemAddressBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new ListingViewHolder(itemQuizListingBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemEmptyModelBinding emptyViewBinding = ItemEmptyModelBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<Address> list) {
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

        void setAddressToProcess(Address address);

        void deleteAddressToProcess(List<Address> modelList, int position);
    }



    public class ListingViewHolder extends BaseViewHolder implements AddressItemViewModel.ModelListener {

        private ItemAddressBinding mBinding;

        private AddressItemViewModel itemViewModel;

        public ListingViewHolder(ItemAddressBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Address blog = modelList.get(position);
            itemViewModel = new AddressItemViewModel(blog, this);
            mBinding.setViewModel(itemViewModel);
            mBinding.executePendingBindings();

            mBinding.addressHead.setOnClickListener(view -> {
                mListener.deleteAddressToProcess(modelList,position);
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.setAddressToProcess(blog);
                }
            });

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