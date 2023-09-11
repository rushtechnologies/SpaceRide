package application.android.com.rushtechnologies.spaceride.Fragments.User.Support;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import application.android.com.rushtechnologies.spaceride.Fragments.Faqs.NewFaqs.NewFaqsFragment;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class SupportReportsFragment extends Fragment {
    private User user;
    private OnSupportReportsFragmentInteractionListener callback;

    public SupportReportsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_support_reports, container, false);
        getUser(callback.getUser());
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            callback = (SupportReportsFragment.OnSupportReportsFragmentInteractionListener) context;
        } catch(Exception ex) {
            System.out.println("Error at NewFaqsFragment: "+ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    public interface OnSupportReportsFragmentInteractionListener {
        User getUser();
        void toast(String message);
    }

    private void getUser(User user) {
        this.user = user;
    }

}
