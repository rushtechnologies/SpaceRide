package application.android.com.rushtechnologies.spaceride.Fragments.User.MyAccount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import application.android.com.rushtechnologies.spaceride.R;

public class MyPasswdFragment extends Fragment {

    public MyPasswdFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_passwd, container, false);

        return view;
    }

}
