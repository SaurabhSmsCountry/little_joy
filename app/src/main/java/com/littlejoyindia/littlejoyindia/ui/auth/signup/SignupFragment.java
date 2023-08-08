package com.littlejoyindia.littlejoyindia.ui.auth.signup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.FragmentSignupBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;
import com.littlejoyindia.littlejoyindia.utils.AppConstants;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;

public class SignupFragment extends BaseFragment<FragmentSignupBinding, SignUpViewModel> implements SignupNavigator {

    private FragmentSignupBinding mBinding;


    private RegisterFragmentListener registerFragmentListener;
    private boolean isAttached = false;


    private static final String TAG = SignupFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;


    private SignUpViewModel mAboutViewModel;

    public static SignupFragment newInstance() {
        Bundle args = new Bundle();
        SignupFragment fragment = new SignupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            registerFragmentListener = (RegisterFragmentListener) context;
            isAttached = true; //flag for whether this framgnet is attache to pager
        } catch (ClassCastException e) {
            throw new ClassCastException(this.toString() + " must implement interface onForgotPasswordInterface");
        }
    }


    public interface RegisterFragmentListener {

        void navigateToLoginUI();
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_signup;
    }

    @Override
    public SignUpViewModel getViewModel() {
        mAboutViewModel = ViewModelProviders.of(this, factory).get(SignUpViewModel.class);
        return mAboutViewModel;
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onSignUpClick() {

        //  [START onSignInClick]
        String fullName = mBinding.etName.getText().toString();
        String emailId = mBinding.etEmailId.getText().toString();
        String referralCode = mBinding.etRefferal.getText().toString();

        if (referralCode.isEmpty()) {
            referralCode = "";
        }

        String gender = "male";
        if (mBinding.spGender.getSelectedItemPosition() == 1) {
            gender = "female";
        }

        if (fullName.isEmpty()) {
            CommonUtils.toastMe("[ Username ] is required", this.getActivity());
            return;
        }

        if (!CommonUtils.isEmailValid(emailId)) {
            CommonUtils.toastMe("[ Email ID ] is not valid", this.getActivity());
            return;
        }

        mAboutViewModel.signUpOnServer(fullName, emailId, gender, referralCode);

    }

    private void customTextView(TextView view) {
        SpannableStringBuilder spanTxt = new SpannableStringBuilder(
                "I agree to ");
        spanTxt.append("Term of services");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {

            }
        }, spanTxt.length() - "Term of services".length(), spanTxt.length(), 0);

        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(spanTxt, TextView.BufferType.SPANNABLE);
    }

    @Override
    public void showLoginUI() {
        if (isAttached) {
            registerFragmentListener.navigateToLoginUI();
        }
    }

    @Override
    public void showToastMessage(String message) {
        CommonUtils.toastMe(message, getActivity());
    }

    @Override
    public void navigateToVerifyOtp() {

    }

    @Override
    public void navigateToDashboardActivity() {
        Intent mIntent = DashboardActivity.newIntent(this.getContext());
        startActivity(mIntent);
        getActivity().finish();
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAboutViewModel.setNavigator(this);
        mBinding = getViewDataBinding();

        customTextView(mBinding.tvBottomPolicy);

        ArrayAdapter<CharSequence> spCustomerType = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.gender, android.R.layout.simple_spinner_item);
        spCustomerType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        getViewDataBinding().spGender.setAdapter(spCustomerType);

    }
}
