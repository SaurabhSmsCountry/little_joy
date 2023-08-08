package com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.banner;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.littlejoyindia.littlejoyindia.data.model.saloon.SalonResponse;
import com.littlejoyindia.littlejoyindia.databinding.ItemDealsBannerBinding;
import com.littlejoyindia.littlejoyindia.databinding.ItemEmptyModelBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewHolder;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsHome.topbrands.BrandsItemViewModel;
import com.littlejoyindia.littlejoyindia.utils.EmptyItemViewModel;

import java.util.List;


public class DealsBannerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<SalonResponse.DealsBannerResponse.DealsBanner> modelList;
    private TopBrandAdapterListener mListener;

    public DealsBannerAdapter(List<SalonResponse.DealsBannerResponse.DealsBanner> modelList) {
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
                ItemDealsBannerBinding itemQuizListingBinding = ItemDealsBannerBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new ListingViewHolder(itemQuizListingBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemEmptyModelBinding emptyViewBinding = ItemEmptyModelBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<SalonResponse.DealsBannerResponse.DealsBanner> list) {
        modelList.clear();
        modelList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItems() {
        modelList.clear();
    }

    public void setListener(TopBrandAdapterListener listener) {
        this.mListener = listener;
    }

    public interface TopBrandAdapterListener {



    }



    public class ListingViewHolder extends BaseViewHolder  {

        private ItemDealsBannerBinding mBinding;

        private BannerItemViewModel itemViewModel;

        public ListingViewHolder(ItemDealsBannerBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final SalonResponse.DealsBannerResponse.DealsBanner blog = modelList.get(position);
            itemViewModel = new BannerItemViewModel(blog);
            mBinding.setViewModel(itemViewModel);
            mBinding.executePendingBindings();

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