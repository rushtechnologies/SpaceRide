package application.android.com.rushtechnologies.spaceride.Fragments.Faqs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView.FaqsRecyclerViewAdapter;
import application.android.com.rushtechnologies.spaceride.DAO.AccessFaq;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpFaq;
import application.android.com.rushtechnologies.spaceride.Model.Faq;
import application.android.com.rushtechnologies.spaceride.R;

public class ShowFaqsFragment extends Fragment {
    private List<Faq> faqs;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public ShowFaqsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_faqs, container, false);
        bindElements(view);
        initView();
        return view;
    }

    private void bindElements(View view) {
        getFaqs();
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new FaqsRecyclerViewAdapter(faqs, R.layout.recycler_view_item, new FaqsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Faq faq, int position) {

            }
        });
    }

    private void initView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getFaqs() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    faqs = new ArrayList<>();
                    final AccessFaq ACCESS_FAQ = new ImpFaq();
                    faqs = ACCESS_FAQ.readAllFaq();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at readAllFaq: " + e.getMessage());
                }
            }
        });
    }

}
