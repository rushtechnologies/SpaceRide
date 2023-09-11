package application.android.com.rushtechnologies.spaceride.App.Adapters.Tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import application.android.com.rushtechnologies.spaceride.Fragments.Faqs.NewFaqs.NewFaqsEventFragment;
import application.android.com.rushtechnologies.spaceride.Fragments.Faqs.NewFaqs.NewFaqsFragment;

public class NewFaqPagerAdapter extends FragmentStatePagerAdapter {
    private int count;

    public NewFaqPagerAdapter(FragmentManager fm, int count) {
        super(fm);
        this.count = count;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new NewFaqsFragment();
            case 1:
                return new NewFaqsEventFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return count;
    }
}
