/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.littlejoyindia.littlejoyindia.data.local.prefs;


import com.littlejoyindia.littlejoyindia.data.DataManager;

public interface PreferencesHelper {


    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getUid();

    void setUid(String fullName);

    void setFullName(String fullName);

    String getFullName();

    void setMobileNumber(String mobileNumber);

    String getMobileNumber();

    void setOtpCode(String otpCode);

    String getOtpCode();

    void setUserType(String userType);

    String getUserType();


    void setLoginType(String loginType);

    String getLoginType();

    void setUserToken(String userToken);

    String getUserToken();

    void setUserAddressAvailable(boolean isAvailable);

    boolean getUserAddressAvailable();


    void setUserTsawId(String tsawId);

    String getUserTsawId();

    void setWelcomeMessageStatus(boolean isWelcomeMessageStatus);

    boolean getWelcomeMessageStatus();

}
