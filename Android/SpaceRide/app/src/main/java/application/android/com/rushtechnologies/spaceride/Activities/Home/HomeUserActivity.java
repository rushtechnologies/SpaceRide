package application.android.com.rushtechnologies.spaceride.Activities.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import application.android.com.rushtechnologies.spaceride.Activities.LogInOrSignInActivity;
import application.android.com.rushtechnologies.spaceride.App.Adapters.Tabs.MyAccountPagerAdapter;
import application.android.com.rushtechnologies.spaceride.DAO.AccessUser;
import application.android.com.rushtechnologies.spaceride.Fragments.User.Support.SupportReportsFragment;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpUser;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.Fragments.User.MyAccount.MyAccountFragment;
import application.android.com.rushtechnologies.spaceride.R;
import application.android.com.rushtechnologies.spaceride.Fragments.User.SpaceRideFragment;
import application.android.com.rushtechnologies.spaceride.Fragments.User.StatsFragment;
import application.android.com.rushtechnologies.spaceride.App.Adapters.Tabs.SupportPagerAdapter;

public class HomeUserActivity extends FragmentActivity implements MyAccountFragment.OnMyAccountFragmentInteractionListener, StatsFragment.OnStatsFragmentInteractionListener, SupportReportsFragment.OnSupportReportsFragmentInteractionListener {
    private DrawerLayout drawerLayout;
    private FrameLayout content_frame;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean internet = false, isSupportInitialized = false, isSupportActive = false, isMyAccountInitialized = false, isMyAccountActive = false;
    private final AccessUser accessUser = new ImpUser();
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);
        bindElements();
        initView();
        listeners();
    }

    private void bindElements() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            internet = true;
            System.out.println("NetworkInfo state: " + networkInfo.getState());
            System.out.println("NetworkInfo extra info: " + networkInfo.getTypeName());
            System.out.println("NetworkInfo extra info: " + networkInfo.getExtraInfo());
            readUser(getIntent().getExtras().getInt("user"));
        } else {
            internet = false;
            toast(1);
        }
        drawerLayout = findViewById(R.id.drawer_layout);
        content_frame = findViewById(R.id.content_frame);
        navigationView = findViewById(R.id.nav_view);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.contentView);
    }

    private void readUser(final int id) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    user = accessUser.readUser(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at readUser: " + e.getMessage());
                }
            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_menu));
        setActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START, true);
            }
        });
    }

    private void initView() {
        setToolbar();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new SpaceRideFragment()).commit();
    }

    private void listeners() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.spaceRide:
                        fragment = new SpaceRideFragment();
                        fragmentTransaction = true;
                        initSingleView();
                        break;
                    case R.id.stats:
                        fragment = new StatsFragment();
                        fragmentTransaction = true;
                        initSingleView();
                        break;
                    case R.id.my_account:
                        fragment = new SpaceRideFragment();
                        fragmentTransaction = true;
                        if(internet) {
                            initMyAccountView();
                        }
                        isMyAccountActive = true;
                        break;
                    case R.id.support:
                        fragment = new SpaceRideFragment();
                        fragmentTransaction = true;
                        if(internet) {
                            initSupportView();
                        }
                        isSupportActive = true;
                        break;
                    case R.id.logout:
                        toast(0);
                        intent(0);
                        break;
                }
                if (fragmentTransaction && internet) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                    menuItem.setChecked(true);
                    getActionBar().setTitle(menuItem.getTitle());
                    drawerLayout.closeDrawers();
                }
                return fragmentTransaction;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.collapse_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                drawerLayout.openDrawer(GravityCompat.START, true);
                return true;
            case R.id.logout:
                toast(0);
                intent(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void intent(int intentCase) {
        if (intentCase == 0) {
            Intent intent = new Intent(this, LogInOrSignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    private void toast(int messageCase) {
        String text = "";
        if (messageCase == 0) {
            text = "¡Hasta luego!";
        } else {
            if (messageCase == 1) {
                text = "No hay conexión a Internet";
            }
        }
        SuperActivityToast.create(this, new Style(), Style.TYPE_BUTTON)
                .setButtonText("¡OK!")
                .setButtonTextColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_DEEP_ORANGE))
                .setButtonTypefaceStyle(Typeface.BOLD)
                .setButtonDividerColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_DEEP_ORANGE))
                .setOnButtonClickListener("My Music", null, new SuperActivityToast.OnButtonClickListener() {
                    @Override
                    public void onClick(View view, Parcelable token) {

                    }
                })
                .setText(text)
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(this.getResources().getColor(R.color.colorPrimaryDark))
                .setTextColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_DEEP_ORANGE))
                .setAnimations(Style.ANIMATIONS_FLY).show();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void toast(String message) {
        SuperActivityToast.create(this, new Style(), Style.TYPE_BUTTON)
                .setButtonText("¡OK!")
                .setButtonTextColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_DEEP_ORANGE))
                .setButtonTypefaceStyle(Typeface.BOLD)
                .setButtonDividerColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_DEEP_ORANGE))
                .setOnButtonClickListener("OK", null, new SuperActivityToast.OnButtonClickListener() {
                    @Override
                    public void onClick(View view, Parcelable token) {

                    }
                })
                .setText(message)
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(this.getResources().getColor(R.color.colorPrimaryDark))
                .setTextColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_DEEP_ORANGE))
                .setAnimations(Style.ANIMATIONS_FLY).show();
    }

    private void addTabsListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void initSingleView() {
        if (isSupportActive || isMyAccountActive) {
            content_frame.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.INVISIBLE);
            viewPager.setVisibility(View.INVISIBLE);
            isSupportActive = false;
        }
    }

    private void initMyAccountView() {
        MyAccountPagerAdapter myAccountPagerAdapter;
        if (!isMyAccountInitialized) {
            content_frame.setVisibility(View.INVISIBLE);
            tabLayout.removeAllTabs();
            tabLayout.setVisibility(View.VISIBLE);
            tabLayout.addTab(tabLayout.newTab().setText("Información"));
            tabLayout.addTab(tabLayout.newTab().setText("Contraseña"));
            myAccountPagerAdapter = new MyAccountPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setVisibility(View.VISIBLE);
            viewPager.setAdapter(myAccountPagerAdapter);
            addTabsListener();
            isMyAccountInitialized = true;
        } else {
            if (isSupportInitialized) {
                tabLayout.removeAllTabs();
                tabLayout.addTab(tabLayout.newTab().setText("Información"));
                tabLayout.addTab(tabLayout.newTab().setText("Contraseña"));
                myAccountPagerAdapter = new MyAccountPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
                viewPager.setAdapter(myAccountPagerAdapter);
            }
            content_frame.setVisibility(View.INVISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);
        }
    }

    private void initSupportView() {
        SupportPagerAdapter supportPagerAdapter;
        if (!isSupportInitialized) {
            content_frame.setVisibility(View.INVISIBLE);
            tabLayout.removeAllTabs();
            tabLayout.setVisibility(View.VISIBLE);
            tabLayout.addTab(tabLayout.newTab().setText("FAQs"));
            tabLayout.addTab(tabLayout.newTab().setText("Reportar"));
            supportPagerAdapter = new SupportPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setVisibility(View.VISIBLE);
            viewPager.setAdapter(supportPagerAdapter);
            addTabsListener();
            isSupportInitialized = true;
        } else {
            if (isMyAccountInitialized) {
                tabLayout.removeAllTabs();
                tabLayout.addTab(tabLayout.newTab().setText("FAQs"));
                tabLayout.addTab(tabLayout.newTab().setText("Reportar"));
                supportPagerAdapter = new SupportPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
                viewPager.setAdapter(supportPagerAdapter);
            }
            content_frame.setVisibility(View.INVISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);
        }
    }

}
