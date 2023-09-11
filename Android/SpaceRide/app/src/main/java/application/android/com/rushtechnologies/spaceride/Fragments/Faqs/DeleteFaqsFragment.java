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

import application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView.DeleteRecyclerViewAdapter;
import application.android.com.rushtechnologies.spaceride.DAO.AccessFaq;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpFaq;
import application.android.com.rushtechnologies.spaceride.Model.Faq;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class DeleteFaqsFragment extends Fragment {
    private List<Faq> faqs;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private boolean deleted;
    private User user;
    private OnDeleteFaqsFragmentInteractionListener callback;

    public DeleteFaqsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_faqs, container, false);
        setUser(callback.getUser());
        bindElements(view);
        setView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnDeleteFaqsFragmentInteractionListener) context;
        } catch (Exception ex) {
            System.out.println("Error at DeleteFaqsFragment: " + ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void bindElements(View view) {
        getFaqs();
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new DeleteRecyclerViewAdapter(R.layout.recycler_view_item_delete, 0, faqs, null, null, new DeleteRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int id, int i) {
                deleteFaq(id);
                if(deleted) {
                    callback.toast("Pregunta frecuente borrada");
                } else {
                    callback.toast("Error al borrar pregunta frecuente");
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
                    final AccessFaq ACCESS_FAQ = new ImpFaq();
                    faqs = ACCESS_FAQ.readAllFaq();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at readAllFaq: " + e.getMessage());
                }
            }
        });
    }

    private void deleteFaq(final int id) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessFaq ACCESS_FAQ = new ImpFaq();
                    deleted = ACCESS_FAQ.deleteFaq(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at createEventReport: " + e.getMessage());
                }
            }
        });
    }

    public void setUser(User user) {
        this.user = user;
    }

    public interface OnDeleteFaqsFragmentInteractionListener {
        User getUser();

        void toast(String message);
    }

}
