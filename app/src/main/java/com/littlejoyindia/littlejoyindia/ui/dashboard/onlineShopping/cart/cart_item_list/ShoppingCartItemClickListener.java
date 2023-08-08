package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.cart_item_list;

public interface ShoppingCartItemClickListener {
    void onItemClickListener(int position);
    void onItemDeleteClickListener(int position);
    void onItemMinusClickListener(int position);
    void onItemAddClickListener(int position);
}
