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

package com.littlejoyindia.littlejoyindia;

import android.app.Activity;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.google.android.libraries.places.api.Places;


import java.util.Locale;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import ss.com.bannerslider.Slider;
import com.littlejoyindia.littlejoyindia.di.component.DaggerAppComponent;
import com.littlejoyindia.littlejoyindia.utils.AppLogger;
import com.littlejoyindia.littlejoyindia.utils.AppSignatureHelper;
import com.littlejoyindia.littlejoyindia.utils.FileUploadNotification;
import com.littlejoyindia.littlejoyindia.utils.PicassoImageLoadingService;



public class LittleJoyIndiaApp extends MultiDexApplication implements HasActivityInjector {


    public static  FileUploadNotification mFileUploadNotification = null;

    public static int totalFileCount = 0;
    public static int totalFileCountUploaded = 0;


    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mFileUploadNotification = new FileUploadNotification(this);


        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        AppLogger.init();

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.api_key), Locale.US);
        }

        Slider.init(new PicassoImageLoadingService(this));


        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

      //  CalligraphyConfig.initDefault(mCalligraphyConfig);

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(mCalligraphyConfig))
                .build());

        AppSignatureHelper appSignatureHelper = new AppSignatureHelper(this);
        appSignatureHelper.getAppSignatures();
        Log.e("Sign",""+appSignatureHelper.getAppSignatures().get(0));
    }
}
