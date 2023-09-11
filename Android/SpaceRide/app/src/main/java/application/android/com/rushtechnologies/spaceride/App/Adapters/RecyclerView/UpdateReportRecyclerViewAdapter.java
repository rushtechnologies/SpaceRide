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
import application.android.com.rushtechnologies.spaceride.Model.MaintenanceReport;
import application.android.com.rushtechnologies.spaceride.R;

public class UpdateReportRecyclerViewAdapter extends RecyclerView.Adapter<UpdateReportRecyclerViewAdapter.ViewHolder> {
    private int layout;
    private boolean maintenance;
    private List<EventReport> eventReports;
    private List<MaintenanceReport> maintenanceReports;
    private OnItemClickListener onItemClickListener;

    public UpdateReportRecyclerViewAdapter(int layout, boolean maintenance, List<EventReport> eventReports, List<MaintenanceReport> maintenanceReports, OnItemClickListener onItemClickListener) {
        this.layout = layout;
        this.maintenance = maintenance;
        this.eventReports = eventReports;
        this.maintenanceReports = maintenanceReports;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        UpdateReportRecyclerViewAdapter.ViewHolder viewHolder = new UpdateReportRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        EventReport eventReport = null;
        MaintenanceReport maintenanceReport = null;
        if (maintenance) {
            maintenanceReport = maintenanceReports.get(i);
        } else {
            eventReport = eventReports.get(i);
        }
        viewHolder.bind(eventReport, maintenanceReport, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        int itemCount;
        if (maintenance) {
            itemCount = maintenanceReports.size();
        } else {
            itemCount = eventReports.size();
        }
        return itemCount;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id, description;
        public EditText solution;
        public Button update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.id = itemView.findViewById(R.id.id);
            this.description = itemView.findViewById(R.id.description);
            this.solution = itemView.findViewById(R.id.solution);
            this.update = itemView.findViewById(R.id.update);
        }

        public void bind(final EventReport eventReport, final MaintenanceReport maintenanceReport, final OnItemClickListener onItemClickListener) {
            final int id;
            if(eventReport != null) {
                id = eventReport.getER_Id();
                this.description.setText("Descripción: " + eventReport.getER_Description());
            } else {
                id = maintenanceReport.getMR_Id();
                this.description.setText("Descripción: " + maintenanceReport.getMR_Description());
            }
            this.id.setText(("ID: " + id));
            this.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(id, solution.getText().toString(), getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(int id, String solution, int i);
    }

}
