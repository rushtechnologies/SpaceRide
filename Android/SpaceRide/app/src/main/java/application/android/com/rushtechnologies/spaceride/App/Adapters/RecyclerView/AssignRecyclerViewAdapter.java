package application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import application.android.com.rushtechnologies.spaceride.R;

public class AssignRecyclerViewAdapter extends RecyclerView.Adapter<AssignRecyclerViewAdapter.ViewHolder> {
    private boolean maintenance;
    private List<String> reports;
    private List<Integer> ids;
    private int layout;
    private Context context;
    private AssignRecyclerViewAdapter.OnAssignFragmentItemClickListener onItemClickListener;

    public AssignRecyclerViewAdapter(boolean maintenance, List<String> reports, List<Integer> ids, int layout, Context context, AssignRecyclerViewAdapter.OnAssignFragmentItemClickListener onItemClickListener) {
        this.maintenance = maintenance;
        this.reports = reports;
        this.ids = ids;
        this.layout = layout;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AssignRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        AssignRecyclerViewAdapter.ViewHolder viewHolder = new AssignRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AssignRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(reports.get(i), ids.get(i), maintenance, context, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView report;
        public Spinner names;
        public Button assign;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.report = itemView.findViewById(R.id.report);
            this.names = itemView.findViewById(R.id.names);
            this.assign = itemView.findViewById(R.id.assign);
        }

        public void bind(final String report, final int id, final boolean maintenance, final Context context, final AssignRecyclerViewAdapter.OnAssignFragmentItemClickListener onItemClickListener) {
            this.report.setText(report);
            addNamesToSpinner(maintenance, context);
            assign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!names.getSelectedItem().toString().equals("Responsable")) {
                        onItemClickListener.onItemClick(report, id, names.getSelectedItem().toString(), maintenance, getAdapterPosition());
                    }
                }
            });
        }

        public void addNamesToSpinner(boolean maintenance, Context context) {
            ArrayAdapter<CharSequence> spinnerAdapter;
            if (maintenance) {
                spinnerAdapter = ArrayAdapter.createFromResource(context, R.array.assign_maintenance, android.R.layout.simple_spinner_item);
            } else {
                spinnerAdapter = ArrayAdapter.createFromResource(context, R.array.assign_event, android.R.layout.simple_spinner_item);
            }
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.names.setAdapter(spinnerAdapter);
        }
    }

    public interface OnAssignFragmentItemClickListener {
        void onItemClick(String report, int id, String name, boolean maintenance, int i);
    }
}
