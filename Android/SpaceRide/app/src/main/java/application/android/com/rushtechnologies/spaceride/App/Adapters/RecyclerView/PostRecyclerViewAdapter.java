package application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import application.android.com.rushtechnologies.spaceride.Model.Faq;
import application.android.com.rushtechnologies.spaceride.R;

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder> {
    private List<Faq> faqs;
    private int layout;
    private OnCheckedChangeListener onCheckedChangeListener;

    public PostRecyclerViewAdapter(List<Faq> faqs, int layout, OnCheckedChangeListener onCheckedChangeListener) {
        this.faqs = faqs;
        this.layout = layout;
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(faqs.get(i), onCheckedChangeListener);
    }

    @Override
    public int getItemCount() {
        return faqs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id, question, answer;
        public Switch post;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.id = itemView.findViewById(R.id.id);
            this.question = itemView.findViewById(R.id.question);
            this.answer = itemView.findViewById(R.id.answer);
            this.post = itemView.findViewById(R.id.post);
        }

        public void bind(final Faq faq, final OnCheckedChangeListener onCheckedChangeListener) {
            this.id.setText("ID: " + faq.getFaq_id());
            this.question.setText("Pregunta: " + faq.getFaq_pregunta());
            this.answer.setText("Respuesta: " + faq.getFaq_respuesta());
            if(faq.isFaq_aprobacion()){
                this.post.setChecked(true);
            } else {
                this.post.setChecked(false);
            }
            this.post.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    onCheckedChangeListener.onCheckedChange(isChecked, faq, getAdapterPosition());
                }
            });
        }

    }

    public interface OnCheckedChangeListener {
        void onCheckedChange(boolean post, Faq faq, int i);
    }

}
