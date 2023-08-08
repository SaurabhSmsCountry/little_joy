package com.littlejoyindia.littlejoyindia.ui.dashboard.referralMembers;

import com.littlejoyindia.littlejoyindia.ui.dashboard.referralMembers.referralModel.ReferralMemberResponse;

public interface ReferralNavigator {
    void showToastMessage(String message);
    void onSuccessReferralMembers(ReferralMemberResponse response);
}
