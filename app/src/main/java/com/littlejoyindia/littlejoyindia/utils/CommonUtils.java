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

package com.littlejoyindia.littlejoyindia.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.util.Patterns;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.littlejoyindia.littlejoyindia.R;

/**
 * Created by amitshekhar on 07/07/17.
 */

public final class CommonUtils {
    private static final Pattern TAG_REGEX = Pattern.compile("<EDU>(.+?)</EDU>", Pattern.DOTALL);
    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static List<String> getTagValues(final String str) {
        final List<String> tagValues = new ArrayList<String>();
        final Matcher matcher = TAG_REGEX.matcher(str);
        while (matcher.find()) {
            tagValues.add(matcher.group(1));
        }
        return tagValues;
    }

    public static String getTimestamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }

    public static String getTimestampForUpload() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT_FOR_UPLOAD, Locale.US).format(new Date());
    }

    public static String getTimestamp(String dateString) {
        String dateToReturn = dateString;
        try {
            Date mDate = new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT_INPUT, Locale.US).parse(dateString);
            dateToReturn =  new  SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return dateToReturn;
        }

    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean checkForEmptyField(String text) {
        return text.isEmpty();
    }

    public static boolean isValidMobileNumber(String mobileNumber) {
        return mobileNumber.length() == 10;
    }



    public static boolean isPasswordValid(String password) {
        if(password.length() > 5){
            return true;
        } else return false;
    }


    public static boolean isPasswordMatched(String password, String confirmPassword) {
        if(password.equals(confirmPassword)){
            return true;
        } else return false;
    }

    public static void toastMe(String message, Context mContext) {
        Toast.makeText(mContext,message,Toast.LENGTH_LONG).show();
    }

    public static Drawable changeBackArrowColor(Context context, int color) {
        String resName;
        int res;

        resName = Build.VERSION.SDK_INT >= 23 ? "abc_ic_ab_back_material" : "abc_ic_ab_back_mtrl_am_alpha";
        res = context.getResources().getIdentifier(resName, "drawable", context.getPackageName());

        final Drawable upArrow = context.getResources().getDrawable(res);
        upArrow.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        return upArrow;
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
