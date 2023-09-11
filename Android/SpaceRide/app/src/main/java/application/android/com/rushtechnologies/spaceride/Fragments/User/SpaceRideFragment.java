package application.android.com.rushtechnologies.spaceride.Fragments.User;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import application.android.com.rushtechnologies.spaceride.R;

public class SpaceRideFragment extends Fragment {

    public SpaceRideFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_space_ride, container, false);
        return view;
    }

}
