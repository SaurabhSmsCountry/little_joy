package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.productListing;

public class FilterModal {
    int id;
    String title;
    String filterType;
    boolean isSelected;

    public FilterModal() {
    }

    public FilterModal(int id, String title, String filterType, boolean isSelected) {
        this.id = id;
        this.title = title;
        this.filterType = filterType;
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
