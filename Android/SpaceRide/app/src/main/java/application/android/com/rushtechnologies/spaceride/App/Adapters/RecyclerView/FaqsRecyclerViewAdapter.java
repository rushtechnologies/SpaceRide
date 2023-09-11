package application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import application.android.com.rushtechnologies.spaceride.Model.Faq;
import application.android.com.rushtechnologies.spaceride.R;

public class FaqsRecyclerViewAdapter extends RecyclerView.Adapter<FaqsRecyclerViewAdapter.ViewHolder> {
    private List<Faq> faqs;
    private int layout;
    private OnItemClickListener onItemClickListener;

    public FaqsRecyclerViewAdapter(List<Faq> faqs, int layout, OnItemClickListener onItemClickListener) {
        this.faqs = faqs;
        this.layout = layout;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        FaqsRecyclerViewAdapter.ViewHolder viewHolder = new FaqsRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(faqs.get(i), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return faqs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView faq_q, faq_ans, faq_topic;

        public ViewHolder(View view) {
            super(view);
            this.faq_q = view.findViewById(R.id.faq_q);
            this.faq_ans = view.findViewById(R.id.faq_ans);
            this.faq_topic = view.findViewById(R.id.faq_topic);
        }

        public void bind(final Faq faq, final OnItemClickListener onItemClickListener){
            this.faq_q.setText(faq.getFaq_pregunta());
            this.faq_topic.setText(faq.getFaq_tema());
            this.faq_ans.setText(faq.getFaq_respuesta());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(faq, getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickListener {
        void OnItemClick(Faq faq, int position);
    }

}
