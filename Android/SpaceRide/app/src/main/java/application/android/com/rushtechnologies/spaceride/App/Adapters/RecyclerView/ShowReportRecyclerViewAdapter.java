package application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import application.android.com.rushtechnologies.spaceride.Model.EventReport;
import application.android.com.rushtechnologies.spaceride.Model.MaintenanceReport;
import application.android.com.rushtechnologies.spaceride.R;

public class ShowReportRecyclerViewAdapter extends RecyclerView.Adapter<ShowReportRecyclerViewAdapter.ViewHolder> {
    private int layout;
    private boolean maintenance;
    private List<EventReport> eventReports;
    private List<MaintenanceReport> maintenanceReports;
    private ShowReportRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public ShowReportRecyclerViewAdapter(int layout, boolean maintenance, List<EventReport> eventReports, List<MaintenanceReport> maintenanceReports, OnItemClickListener onItemClickListener) {
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
        ShowReportRecyclerViewAdapter.ViewHolder viewHolder = new ShowReportRecyclerViewAdapter.ViewHolder(view);
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
        private TextView id, date, description, status, solution;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.id = itemView.findViewById(R.id.id);
            this.date = itemView.findViewById(R.id.date);
            this.description = itemView.findViewById(R.id.description);
            this.status = itemView.findViewById(R.id.status);
            this.solution = itemView.findViewById(R.id.solution);
        }

        public void bind(final EventReport eventReport, final MaintenanceReport maintenanceReport, final ShowReportRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
            final int id;
            if(eventReport != null) {
                id = eventReport.getER_Id();
                this.date.setText("Date: " + eventReport.getER_Timestamp().toString());
                this.description.setText("Description: " + eventReport.getER_Description());
                this.status.setText("Status: " + eventReport.getER_Status());
                this.solution.setText("Solution: " + eventReport.getER_Solution());
            } else {
                id = maintenanceReport.getMR_Id();
                this.date.setText("Date: " + maintenanceReport.getMR_Timestamp().toString());
                this.description.setText("Description: " + maintenanceReport.getMR_Description());
                this.status.setText("Status: " + maintenanceReport.getMR_Status());
                this.solution.setText("Solution: " + maintenanceReport.getMR_Solution());
            }
            this.id.setText(("ID: " + id));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(id, getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickListener {
        void OnItemClick(int id, int i);
    }

}
