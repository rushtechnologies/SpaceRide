package application.android.com.rushtechnologies.spaceride.App.Adapters.Tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import application.android.com.rushtechnologies.spaceride.Fragments.User.MyAccount.MyAccountFragment;
import application.android.com.rushtechnologies.spaceride.Fragments.User.MyAccount.MyPasswdFragment;
import application.android.com.rushtechnologies.spaceride.Fragments.User.Support.SupportFaqsFragment;
import application.android.com.rushtechnologies.spaceride.Fragments.User.Support.SupportReportsFragment;

public class MyAccountPagerAdapter extends FragmentStatePagerAdapter {
    private int tabs;

    public MyAccountPagerAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new MyAccountFragment();
            case 1:
                return new MyPasswdFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
