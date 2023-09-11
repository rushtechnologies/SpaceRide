package application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import application.android.com.rushtechnologies.spaceride.Model.Faq;
import application.android.com.rushtechnologies.spaceride.R;

public class UpdateFaqsRecyclerViewAdapter extends RecyclerView.Adapter<UpdateFaqsRecyclerViewAdapter.ViewHolder> {
    private List<Faq> faqs;
    private int layout;
    private OnItemClickListener onItemClickListener;

    public UpdateFaqsRecyclerViewAdapter(List<Faq> faqs, int layout, OnItemClickListener onItemClickListener) {
        this.faqs = faqs;
        this.layout = layout;
        this.onItemClickListener = onItemClickListener;
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
        viewHolder.bind(faqs.get(i), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return faqs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id, question;
        public Button update;
        public EditText answer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.id = itemView.findViewById(R.id.id);
            this.question = itemView.findViewById(R.id.question);
            this.answer = itemView.findViewById(R.id.answer);
            this.update = itemView.findViewById(R.id.update);
        }

        public void bind(final Faq faq, final OnItemClickListener onItemClickListener) {
            this.id.setText("ID: " + faq.getFaq_id());
            this.question.setText("Pregunta: " + faq.getFaq_pregunta());
            this.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(faq, answer.getText().toString(), getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickListener {
        void OnItemClick(Faq faq, String answer, int position);
    }

}
