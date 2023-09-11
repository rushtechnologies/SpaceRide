package application.android.com.rushtechnologies.spaceride.App.Adapters.Tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import application.android.com.rushtechnologies.spaceride.Fragments.User.Support.SupportFaqsFragment;
import application.android.com.rushtechnologies.spaceride.Fragments.User.Support.SupportReportsFragment;

public class SupportPagerAdapter extends FragmentStatePagerAdapter {
    private int tabs;

    public SupportPagerAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new SupportFaqsFragment();
            case 1:
                return new SupportReportsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
