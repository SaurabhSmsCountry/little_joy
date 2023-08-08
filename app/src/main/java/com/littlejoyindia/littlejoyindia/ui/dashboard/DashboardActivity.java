package com.littlejoyindia.littlejoyindia.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import com.google.android.material.navigation.NavigationView;
import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.ui.dashboard.deals.MyDealsFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.dealsCity.DealsCityActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet.MyWalletFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.OnlineShoppingFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.cart.ui.OnlineShoppingCartActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.ShoppingOrderListScreenActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.orders.OrderFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.profile.ProfileFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.referAndEarn.ReferAndEarnFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.referralMembers.ReferralFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.SalonFragment;
import com.littlejoyindia.littlejoyindia.ui.dashboard.salon.subCat.SalonCatFragment;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.databinding.ActivityDashboardBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.home.HomeFragment;
import com.littlejoyindia.littlejoyindia.ui.splash.SplashActivity;
import com.littlejoyindia.littlejoyindia.utils.CommonUtils;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardViewModel>
        implements DashboardNavigator, HasSupportFragmentInjector, HomeFragment.FragmentListener,
        SalonFragment.FragmentListener, SalonCatFragment.FragmentListener, OnlineShoppingFragment.FragmentListener {


    public static final String TAG = DashboardActivity.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private DashboardViewModel viewModel;
    private ActivityDashboardBinding mActivityMainBinding;
    private DrawerLayout mDrawer;
    private DashboardViewModel mMainViewModel;
    private Toolbar mToolbar;
    private Menu menu;


    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, DashboardActivity.class);
    }

    @Override
    public DashboardViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, (ViewModelProvider.Factory) factory).get(DashboardViewModel.class);
        return viewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setStatusBarTransparent();
        mActivityMainBinding = getViewDataBinding();
        viewModel.setNavigator(this);

        viewModel.setDefaults();


        mDrawer = mActivityMainBinding.drawerView;
        mToolbar = mActivityMainBinding.toolbar;
        mToolbar.setTitle("LittleJoy");


        // mToolbar.setLogo(R.drawable.logo_home);
        // getSupportActionBar().setDisplayUseLogoEnabled(true);
        //   mToolbar.setNavigationIcon(R.drawable.left_menu);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(false);

        mDrawerToggle.setHomeAsUpIndicator(R.drawable.menu_icon);

        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        Menu m = navView.getMenu();

//        navView.setVisibility(View.GONE);

        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openDrawer(GravityCompat.START);
            }
        });

        navView.setNavigationItemSelectedListener(item -> {
            mDrawer.close();
            /*if (item.getItemId() == R.id.nav_order){
//                navView.setVisibility(View.GONE);
                redirectToOrderListScreen();
            } else */if (item.getItemId() == R.id.nav_referral_members){
                redirectToReferralScreen();
            }  else if (item.getItemId() == R.id.nav_order){
                redirectToOrderListScreen();
            }  else if (item.getItemId() == R.id.nav_my_orders){
                redirectToOrderScreen();
            }  else if (item.getItemId() == R.id.nav_deals){
                redirectToDealsScreen();
            }  else if (item.getItemId() == R.id.profile){
                onClickAccount();
            }  else if (item.getItemId() == R.id.nav_wallet){
                onClickWallet();
            }  else if (item.getItemId() == R.id.nav_home){
                onClickHome();
            }  else if (item.getItemId() == R.id.logout){
                logout();
            }
            else {}
            return false;
        });

        if (getIntent().getExtras() != null) {
            int page = getIntent().getExtras().getInt("page", 0);
            if (page == 1) {
                redirectToOrderScreen();
            } else if (page == 2) {
                redirectToDealsScreen();
            }
        }
    }

    private void logout() {
        viewModel.getDataManager().setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void redirectToDealsScreen() {
        resetBottom();
        mActivityMainBinding.ivHome.setImageResource(R.drawable.home_selected);
        mActivityMainBinding.tvHome.setTextColor(getColor(R.color.white));
        getSupportFragmentManager()
                .beginTransaction()
                //.disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.clRootView, MyDealsFragment.newInstance(), MyDealsFragment.TAG)
                // .addToBackStack(null)
                .commit();
    }

    private void redirectToOrderScreen() {
        resetBottom();
        mActivityMainBinding.ivHome.setImageResource(R.drawable.home_selected);
        mActivityMainBinding.tvHome.setTextColor(getColor(R.color.white));
        getSupportFragmentManager()
                .beginTransaction()
                //.disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.clRootView, OrderFragment.newInstance(), OrderFragment.TAG)
                // .addToBackStack(null)
                .commit();
    }

    private void redirectToReferralScreen() {
        resetBottom();
        mActivityMainBinding.ivRefer.setImageResource(R.drawable.refer_selected);
        mActivityMainBinding.tvRefer.setTextColor(getColor(R.color.white));
        getSupportFragmentManager()
                .beginTransaction()
                //.disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.clRootView, ReferralFragment.newInstance(), ReferralFragment.TAG)
                // .addToBackStack(null)
                .commit();
    }

    private void redirectToOrderListScreen() {
        Intent intent = ShoppingOrderListScreenActivity.newIntent(this);
        startActivity(intent);
    }

    public void hideBottomMenu() {
        getViewDataBinding().cv.setVisibility(View.GONE);
    }

    /*private void setStatusBarTransparent() {

        //make translucent statusBar on kitkat devices
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }*/

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {

        FragmentManager fm = getSupportFragmentManager();
//        for (Fragment frag : fm.getFragments()) {
//            if (frag.isVisible()) {
//                FragmentManager childFm = frag.getChildFragmentManager();
//                if (childFm.getBackStackEntryCount() > 0) {
//                    childFm.popBackStack();
//                    return;
//                }
//            }
//        }

        if (fm.getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.clRootView);
            Log.e("BACK PRESSED", "BACK PRESSED");

            fm.popBackStackImmediate();


        }
    }


    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    //.disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    public void showToastMessage(String message) {
        CommonUtils.toastMe(message, this);
    }

    private void resetBottom() {
        mActivityMainBinding.ivHome.setImageResource(R.drawable.home_unselected);
        mActivityMainBinding.tvHome.setTextColor(getColor(R.color.translucent_white));
        mActivityMainBinding.ivRefer.setImageResource(R.drawable.refer_unselected);
        mActivityMainBinding.tvRefer.setTextColor(getColor(R.color.translucent_white));
        mActivityMainBinding.ivWallet.setImageResource(R.drawable.wallet_unselected);
        mActivityMainBinding.tvWallet.setTextColor(getColor(R.color.translucent_white));
        mActivityMainBinding.ivAccount.setImageResource(R.drawable.myaccount_unseleted);
        mActivityMainBinding.tvAccount.setTextColor(getColor(R.color.translucent_white));
    }

    @Override
    public void onClickHome() {
        resetBottom();
        mActivityMainBinding.ivHome.setImageResource(R.drawable.home_selected);
        mActivityMainBinding.tvHome.setTextColor(getColor(R.color.white));
        getSupportFragmentManager()
                .beginTransaction()
                //.disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.clRootView, HomeFragment.newInstance(), HomeFragment.TAG)
                // .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClickReferAndEarn() {
        resetBottom();
        mActivityMainBinding.ivRefer.setImageResource(R.drawable.refer_selected);
        mActivityMainBinding.tvRefer.setTextColor(getColor(R.color.white));
        getSupportFragmentManager()
                .beginTransaction()
                //.disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.clRootView, ReferAndEarnFragment.newInstance(), ReferAndEarnFragment.TAG)
                // .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClickWallet() {
        resetBottom();
        mActivityMainBinding.ivWallet.setImageResource(R.drawable.wallet_selected);
        mActivityMainBinding.tvWallet.setTextColor(getColor(R.color.white));
        getSupportFragmentManager()
                .beginTransaction()
                //.disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.clRootView, MyWalletFragment.newInstance(), MyWalletFragment.TAG)
                // .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClickAccount() {
        resetBottom();
        mActivityMainBinding.ivAccount.setImageResource(R.drawable.myaccount_seleted);
        mActivityMainBinding.tvAccount.setTextColor(getColor(R.color.white));
        getSupportFragmentManager()
                .beginTransaction()
                //.disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.clRootView, ProfileFragment.newInstance(), ProfileFragment.TAG)
                // .addToBackStack(null)
                .commit();
    }


    @Override
    public void showHomeFragment() {
        // lockDrawer();
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }

        getSupportFragmentManager()
                .beginTransaction()
                //.disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .add(R.id.clRootView, HomeFragment.newInstance(), HomeFragment.TAG)
                // .addToBackStack(null)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actionCart) {
            if (mToolbar.getTitle().toString().equals("Online Shopping")) {
                Intent intent = OnlineShoppingCartActivity.newIntent(this);
                startActivity(intent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void navigateToSalonServices() {

        hideBottomMenu();

        getSupportFragmentManager()
                .beginTransaction()
                //.disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.clRootView, SalonFragment.newInstance(), SalonFragment.TAG)
                // .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void navigateToSalonDeals() {
//        Intent mIntent = DealsCityActivity.newIntent(this);
//        startActivity(mIntent);
//        Intent mIntent = DealsHomeActivity.newIntent(this);
        Intent mIntent = DealsCityActivity.newIntent(this);
        mIntent.putExtra("userCity", "Jammu");
        mIntent.putExtra("latitude", "0");
        mIntent.putExtra("longitude", "0");
        startActivity(mIntent);
    }

    @Override
    public void navigateToOnlineShopping() {
        hideBottomMenu();

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.clRootView, OnlineShoppingFragment.newInstance(), OnlineShoppingFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void setHeaderText(String headerText) {
        mToolbar.setTitle(headerText);
    }

    @Override
    public void setHeaderColor(int color) {
        mToolbar.setBackgroundColor(getColor(color));
    }
}