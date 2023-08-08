package com.littlejoyindia.littlejoyindia.ui.auth.login;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.FragmentLoginBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;
import com.littlejoyindia.littlejoyindia.utils.AppConstants;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;

import static com.facebook.FacebookSdk.getApplicationContext;

public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginViewModel> implements LoginNavigator {


    private FragmentLoginBinding mBinding;

    private static final String TAG = LoginFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private LoginViewModel mAboutViewModel;


    private LoginFragmentListener loginFragmentListener;
    private boolean isAttached = false;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            loginFragmentListener = (LoginFragmentListener) context;
            isAttached = true; //flag for whether this framgnet is attache to pager
        } catch (ClassCastException e) {
            throw new ClassCastException(this.toString() + " must implement interface onForgotPasswordInterface");
        }
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        mAboutViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        return mAboutViewModel;
    }


//    @Override
//    public void navigateToDashboardActivity() {
//        Intent mIntent = DashboardActivity.newIntent(this.getContext());
//        startActivity(mIntent);
//        getActivity().finish();
//    }


    @Override
    public void navigateToVerifyOtp() {
        if (isAttached) {
            loginFragmentListener.navigateToVerifyOtp();
        }
    }


    @Override
    public void onSignInClick() {
        // [START onSignInClick]
        String emailAddress = mBinding.etEmailId.getText().toString();


        if (emailAddress.isEmpty()) {
            CommonUtils.toastMe("[ Mobile Number ] is required", this.getActivity());
            return;
        }


        mAboutViewModel.signInOnServer(AppConstants.LOGIN_TYPE_MOBILE, emailAddress);

    }

    private void customTextView(TextView view) {
        SpannableStringBuilder spanTxt = new SpannableStringBuilder(
                "By continuing, you agree to our ");
        spanTxt.append("Term of services");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {

            }
        }, spanTxt.length() - "Term of services".length(), spanTxt.length(), 0);
        spanTxt.append(" &");
        spanTxt.setSpan(new ForegroundColorSpan(Color.BLACK), 32, spanTxt.length(), 0);
        spanTxt.append(" Privacy Policy");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {

            }
        }, spanTxt.length() - " Privacy Policy".length(), spanTxt.length(), 0);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(spanTxt, TextView.BufferType.SPANNABLE);
    }


    @Override
    public void showToastMessage(String message) {
        CommonUtils.toastMe(message, getActivity());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAboutViewModel.setNavigator(this);
        mBinding = getViewDataBinding();

        customTextView(getViewDataBinding().tvBottomPolicy);
    }

    public interface LoginFragmentListener {

        void navigateToVerifyOtp();

    }


}
