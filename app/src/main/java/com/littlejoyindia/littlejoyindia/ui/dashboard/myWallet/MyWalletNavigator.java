package com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet;

import com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet.myWalletModel.*;

public interface MyWalletNavigator {
    void showToastMessage(String message);
    void onSuccessMyWallet(MyWalletResponse response);
    void onSuccessMyWalletHistory(MyWalletHistoryResponse response);
}
