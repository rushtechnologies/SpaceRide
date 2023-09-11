package application.android.com.rushtechnologies.spaceride.Fragments.Faqs;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView.PostRecyclerViewAdapter;
import application.android.com.rushtechnologies.spaceride.DAO.AccessFaq;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpFaq;
import application.android.com.rushtechnologies.spaceride.Model.Faq;
import application.android.com.rushtechnologies.spaceride.R;

public class PostFaqsFragment extends Fragment {
    private List<Faq> faqs;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private OnPostFaqsFragmentInteractionListener callback;

    private boolean updated;

    public PostFaqsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_faqs, container, false);
        bindElements(view);
        setView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnPostFaqsFragmentInteractionListener) context;
        } catch (Exception ex) {
            System.out.println("Error at PostFaqsFragment: " + ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void bindElements(View view) {
        getFaqs();
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new PostRecyclerViewAdapter(faqs, R.layout.recycler_view_item_post, new PostRecyclerViewAdapter.OnCheckedChangeListener() {
            @Override
            public void onCheckedChange(boolean post, Faq faq, int i) {
                updateFaq_approval(faq.getFaq_id(), post);
                if(updated) {
                    callback.toast("Pregunta frecuente publicada");
                } else {
                    callback.toast("Error al publicar pregunta frecuente");
                }
            }
        });
    }

    private void setView() {
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

    private void updateFaq_approval(final int id, final boolean approval) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessFaq ACCESS_FAQ = new ImpFaq();
                    updated = ACCESS_FAQ.updateFaq_approval(id, approval);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at createEventReport: " + e.getMessage());
                }
            }
        });
    }

    public interface OnPostFaqsFragmentInteractionListener{
        void toast(String message);
    }

}
