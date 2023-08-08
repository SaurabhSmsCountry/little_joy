package com.littlejoyindia.littlejoyindia.ui.auth.verify;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;


import javax.inject.Inject;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.FragmentVerifyOtpBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;
import com.littlejoyindia.littlejoyindia.utils.AppSignatureHashHelper;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;
import com.littlejoyindia.littlejoyindia.utils.SMSReceiver;

public class VerifyOtpFragment extends BaseFragment<FragmentVerifyOtpBinding, VerifyOtpViewModel>
        implements VerifyOtpNavigator, SMSReceiver.OTPReceiveListener {

    public static final String TAG = VerifyOtpFragment.class.getSimpleName();

    private SMSReceiver smsReceiver;

    @Inject
    ViewModelProviderFactory factory;
    private VerifyOtpViewModel viewModel;

    private FragmentListener fragmentListener;
    private boolean isAttached = false;

    private FragmentVerifyOtpBinding mOtpBinding;

    @Override
    public void onOTPReceived(String otp) {

        // InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        // imm.hideSoftInputFromWindow(otp_view.getWindowToken(), 0);


        if (otp.length() == 4) {
            mOtpBinding.etFirst.setText(otp.charAt(0));
            mOtpBinding.etSecond.setText(otp.charAt(1));
            mOtpBinding.etThird.setText(otp.charAt(2));
            mOtpBinding.etFour.setText(otp.charAt(3));
        }

        viewModel.verifyOtpOnServer(otp);

        if (smsReceiver != null) {
            getActivity().unregisterReceiver(smsReceiver);
            smsReceiver = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (smsReceiver != null) {
            getActivity().unregisterReceiver(smsReceiver);
        }
    }

    @Override
    public void onOTPTimeOut() {

    }

    @Override
    public void onOTPReceivedError(String error) {

    }

    private void startSMSListener() {
        try {
            smsReceiver = new SMSReceiver();
            smsReceiver.setOTPListener(this);

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
            getActivity().registerReceiver(smsReceiver, intentFilter);

            SmsRetrieverClient client = SmsRetriever.getClient(getActivity());

            Task<Void> task = client.startSmsRetriever();
            task.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    // API successfully started
                }
            });

            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Fail to start API
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface FragmentListener {

        void navigateToPersonalProfile();

        void navigateToLogin();
    }

    public static VerifyOtpFragment newInstance() {
        Bundle args = new Bundle();
        VerifyOtpFragment fragment = new VerifyOtpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentListener = (FragmentListener) context;
            isAttached = true; //flag for whether this framgnet is attache to pager
        } catch (ClassCastException e) {
            throw new ClassCastException(this.toString() + " must implement interface FragmentListener");
        }
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_verify_otp;
    }

    @Override
    public VerifyOtpViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(VerifyOtpViewModel.class);
        return viewModel;
    }

    @Override
    public void goBack() {
        if (isAttached) {
            fragmentListener.navigateToLogin();
        }
    }


    @Override
    public void navigateToDashboardActivity() {
        Intent mIntent = DashboardActivity.newIntent(getActivity());
        startActivity(mIntent);
        getActivity().finish();
    }

    @Override
    public void onVerifyClicked() {


        String s1 = getViewDataBinding().etFirst.getText().toString();
        String s2 = getViewDataBinding().etSecond.getText().toString();
        String s3 = getViewDataBinding().etThird.getText().toString();
        String s4 = getViewDataBinding().etFour.getText().toString();
        if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty()) {
            CommonUtils.toastMe("[ OTP ] not valid !", getActivity());
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(s1);
        sb.append(s2);
        sb.append(s3);
        sb.append(s4);

        viewModel.verifyOtpOnServer(sb.toString());

    }

    @Override
    public void onResendOtpClicked() {

        getViewDataBinding().etSecond.getText().clear();
        getViewDataBinding().etThird.getText().clear();
        getViewDataBinding().etFour.getText().clear();
        getViewDataBinding().etFirst.getText().clear();
        setDigitLocation(0);
        viewModel.requestOtpAgain();
    }


    @Override
    public void setDigitLocation(int location) {


        switch (location) {

            case 0:

                if (mOtpBinding != null) {
                    mOtpBinding.etFirst.getText().clear();
                    mOtpBinding.etSecond.getText().clear();
                    mOtpBinding.etThird.getText().clear();
                    mOtpBinding.etFour.getText().clear();

                    mOtpBinding.etFirst.requestFocus();
                }

                break;


            case 1:
                getViewDataBinding().etSecond.getText().clear();
                getViewDataBinding().etThird.getText().clear();
                getViewDataBinding().etFour.getText().clear();

                getViewDataBinding().etSecond.requestFocus();
                break;
            case 2:
                getViewDataBinding().etThird.getText().clear();
                getViewDataBinding().etFour.getText().clear();
                getViewDataBinding().etThird.requestFocus();


                break;

            case 3:
                getViewDataBinding().etFour.getText().clear();
                getViewDataBinding().etFour.requestFocus();
                break;

            case 4:
                break;

        }
    }

    @Override
    public void showToastMessage(String message) {
        CommonUtils.toastMe(message, getActivity());
    }

    @Override
    public void showRegisterUI() {
        if (isAttached) {
            fragmentListener.navigateToPersonalProfile();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        mOtpBinding = getViewDataBinding();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.setupInitialUI();
        getViewDataBinding().tvMobile.setText("+91 " + viewModel.getDataManager().getMobileNumber());

        AppSignatureHashHelper appSignatureHashHelper = new AppSignatureHashHelper(getActivity());
        Log.e(TAG, "HashKey: " + appSignatureHashHelper.getAppSignatures().get(0));
        startSMSListener();

    }
}
