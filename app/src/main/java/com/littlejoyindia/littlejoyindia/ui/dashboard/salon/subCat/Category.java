package com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat;


import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class Category implements Parent<Services> {

    private List<Services> mIngredients;

    public String getName() {
        return name;
    }

    private String name;

    public Category(String name, List<Services> ingredients) {
        mIngredients = ingredients;
        this.name = name;
    }

    @Override
    public List<Services> getChildList() {
        return mIngredients;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
