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

import android.content.Context;
import android.content.SharedPreferences;



import javax.inject.Inject;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.di.PreferenceInfo;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_TYPE = "PREF_KEY_USER_TYPE";

    private static final String PREF_KEY_USER_UID = "PREF_KEY_USER_UID";

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";

    private static final String PREF_KEY_USER_FULL_NAME = "PREF_KEY_USER_FULL_NAME";
    private static final String PREF_KEY_USER_EMAIL_ID = "PREF_KEY_USER_EMAIL_ID";
    private static final String PREF_KEY_USER_MOBILE_NUMBER = "PREF_KEY_USER_MOBILE_NUMBER";
    private static final String PREF_KEY_USER_PASSWORD = "PREF_KEY_USER_PASSWORD";
    private static final String PREF_KEY_USER_TEACHER_REFERENCE = "PREF_KEY_USER_TEACHER_REFERENCE";
    private static final String PREF_KEY_USER_USER_TYPE = "PREF_KEY_USER_USER_TYPE";
    private static final String PREF_KEY_USER_LOGIN_TYPE = "PREF_KEY_USER_LOGIN_TYPE";
    private static final String PREF_KEY_USER_OTP_CODE = "PREF_KEY_USER_OTP_CODE";
    private static final String PREF_KEY_USER_DEVICE = "PREF_KEY_USER_DEVICE";
    private static final String PREF_KEY_USER_SOCIAL_ID = "PREF_KEY_USER_SOCIAL_ID";
    private static final String PREF_KEY_USER_CREATED_AT = "PREF_KEY_USER_CREATED_AT";
    private static final String PREF_KEY_USER_IS_LOGGED_IN = "PREF_KEY_USER_IS_LOGGED_IN";


    //

    private static final String PREF_KEY_USER_TOKEN = "PREF_KEY_USER_TOKEN";

    private static final String PREF_KEY_IS_ADDRESS_AVAILABLE = "PREF_KEY_IS_ADDRESS_AVAILABLE";
    private static final String PREF_KEY_TSAWID = "PREF_KEY_TSAWID";

    private static final String PREF_IS_WELCOME_MESSAGE = "PREF_IS_WELCOME_MESSAGE";

    private static SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    public static String getUserTypeGlobal() {
        return mPrefs.getString(PREF_KEY_USER_TYPE, null);
    }

    public static String getUserID() {
        return mPrefs.getString(PREF_KEY_USER_TYPE, null);
    }


    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getUid() {
        return mPrefs.getString(PREF_KEY_USER_UID,null);
    }

    @Override
    public void setUid(String uid) {
        mPrefs.edit().putString(PREF_KEY_USER_UID, uid).apply();
    }

    @Override
    public void setFullName(String fullName) {
        mPrefs.edit().putString(PREF_KEY_USER_FULL_NAME, fullName).apply();
    }

    @Override
    public String getFullName() {
        return mPrefs.getString(PREF_KEY_USER_FULL_NAME,null);
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        mPrefs.edit().putString(PREF_KEY_USER_MOBILE_NUMBER, mobileNumber).apply();
    }

    @Override
    public String getMobileNumber() {
        return mPrefs.getString(PREF_KEY_USER_MOBILE_NUMBER,null);
    }

    @Override
    public void setOtpCode(String otpCode) {
        mPrefs.edit().putString(PREF_KEY_USER_OTP_CODE, otpCode).apply();
    }

    @Override
    public String getOtpCode() {
        return mPrefs.getString(PREF_KEY_USER_OTP_CODE,null);
    }

    @Override
    public void setUserType(String userType) {
        mPrefs.edit().putString(PREF_KEY_USER_USER_TYPE, userType).apply();
    }

    @Override
    public String getUserType() {
        return mPrefs.getString(PREF_KEY_USER_USER_TYPE,null);
    }

    @Override
    public void setLoginType(String loginType) {
        mPrefs.edit().putString(PREF_KEY_USER_LOGIN_TYPE, loginType).apply();
    }

    @Override
    public String getLoginType() {
        return mPrefs.getString(PREF_KEY_USER_LOGIN_TYPE,null);
    }

    @Override
    public void setUserToken(String userToken) {
        mPrefs.edit().putString(PREF_KEY_USER_TOKEN, userToken).apply();
    }

    @Override
    public String getUserToken() {
        return mPrefs.getString(PREF_KEY_USER_TOKEN,null);
    }

    @Override
    public void setUserAddressAvailable(boolean isAvailable) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_ADDRESS_AVAILABLE, isAvailable).apply();
    }

    @Override
    public boolean getUserAddressAvailable() {
        return mPrefs.getBoolean(PREF_KEY_IS_ADDRESS_AVAILABLE,false);
    }

    @Override
    public void setUserTsawId(String tsawId) {
        mPrefs.edit().putString(PREF_KEY_TSAWID, tsawId).apply();
    }

    @Override
    public String getUserTsawId() {
        return mPrefs.getString(PREF_KEY_TSAWID,null);
    }

    @Override
    public void setWelcomeMessageStatus(boolean isWelcomeMessageStatus) {
        mPrefs.edit().putBoolean(PREF_IS_WELCOME_MESSAGE, isWelcomeMessageStatus).apply();
    }

    @Override
    public boolean getWelcomeMessageStatus() {
        return mPrefs.getBoolean(PREF_IS_WELCOME_MESSAGE,false);
    }
}
