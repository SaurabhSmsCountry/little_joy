package com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class SalonServicesCategoryModel implements Parent<Services>  {

    String categoryName;
    List<Services> mServiceList;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Services> getmServiceList() {
        return mServiceList;
    }

    public void setmServiceList(List<Services> mServiceList) {
        this.mServiceList = mServiceList;
    }


    @Override
    public List<Services> getChildList() {
        return mServiceList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
