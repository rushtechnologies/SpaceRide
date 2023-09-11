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

import application.android.com.rushtechnologies.spaceride.Model.EventReport;
import application.android.com.rushtechnologies.spaceride.R;

public class NewFaqRecyclerViewAdapter extends RecyclerView.Adapter<NewFaqRecyclerViewAdapter.ViewHolder> {
    private int layout;
    private List<EventReport> eventReports;
    private OnItemClickListener onItemClickListener;

    public NewFaqRecyclerViewAdapter(int layout, List<EventReport> eventReports, OnItemClickListener onItemClickListener) {
        this.layout = layout;
        this.eventReports = eventReports;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        NewFaqRecyclerViewAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(eventReports.get(i), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return eventReports.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public EditText question;
        public Button create;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.description = itemView.findViewById(R.id.description);
            this.question = itemView.findViewById(R.id.question);
            this.create = itemView.findViewById(R.id.create);
        }

        public void bind(final EventReport eventReport, final OnItemClickListener onItemClickListener) {
            this.description.setText(eventReport.getER_Description());
            this.create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(eventReport.getER_Id(), question.getText().toString(), getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickListener {
        void OnItemClick(int id, String question, int i);
    }

}
