package application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import application.android.com.rushtechnologies.spaceride.Model.EventReport;
import application.android.com.rushtechnologies.spaceride.Model.Faq;
import application.android.com.rushtechnologies.spaceride.Model.MaintenanceReport;
import application.android.com.rushtechnologies.spaceride.R;

public class DeleteRecyclerViewAdapter extends RecyclerView.Adapter<DeleteRecyclerViewAdapter.ViewHolder> {
    private int layout, deleteCase;
    private List<Faq> faqs;
    private List<EventReport> eventReports;
    private List<MaintenanceReport> maintenanceReports;
    private OnItemClickListener onItemClickListener;

    public DeleteRecyclerViewAdapter(int layout, int deleteCase, List<Faq> faqs, List<EventReport> eventReports, List<MaintenanceReport> maintenanceReports, OnItemClickListener onItemClickListener) {
        this.layout = layout;
        this.deleteCase = deleteCase;
        this.faqs = faqs;
        this.eventReports = eventReports;
        this.maintenanceReports = maintenanceReports;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        DeleteRecyclerViewAdapter.ViewHolder viewHolder = new DeleteRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Faq faq = null;
        EventReport eventReport = null;
        MaintenanceReport maintenanceReport = null;
        switch (deleteCase) {
            case 0:
                faq = faqs.get(i);
                break;
            case 1:
                eventReport = eventReports.get(i);
                break;
            case 2:
                maintenanceReport = maintenanceReports.get(i);
                break;
        }
        viewHolder.bind(faq, eventReport, maintenanceReport, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        switch (deleteCase) {
            case 0:
                itemCount = faqs.size();
                break;
            case 1:
                itemCount = eventReports.size();
                break;
            case 2:
                itemCount = maintenanceReports.size();
                break;
        }
        return itemCount;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id, description, solution;
        public Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.id = itemView.findViewById(R.id.id);
            this.description = itemView.findViewById(R.id.description);
            this.delete = itemView.findViewById(R.id.delete);
            this.solution = itemView.findViewById(R.id.solution);
        }

        public void bind(final Faq faq, final EventReport eventReport, final MaintenanceReport maintenanceReport, final OnItemClickListener onItemClickListener) {
            final int id = getId(faq, eventReport, maintenanceReport)[0];
            int caseView = getId(faq, eventReport, maintenanceReport)[1];
            switch (caseView) {
                case 0:
                    this.description.setText("Pregunta: " + faq.getFaq_pregunta());
                    this.solution.setText("Respuesta: " + faq.getFaq_respuesta());
                    break;
                case 1:
                    this.description.setText("Descripción: " + eventReport.getER_Description());
                    this.solution.setText("Solución: " + eventReport.getER_Solution());
                    break;
                case 2:
                    this.description.setText("Descripción: " + maintenanceReport.getMR_Description());
                    this.solution.setText("Solución: " + maintenanceReport.getMR_Solution());
                    break;
            }
            this.id.setText("ID: " + id);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(id, getAdapterPosition());
                }
            });
        }

        public int[] getId(Faq faq, EventReport eventReport, MaintenanceReport maintenanceReport) {
            int id = 0;
            int caseView = 0;
            if (faq != null) {
                id = faq.getFaq_id();
            } else {
                if (eventReport != null) {
                    id = eventReport.getER_Id();
                    caseView = 1;
                } else {
                    if (maintenanceReport != null) {
                        id = maintenanceReport.getMR_Id();
                        caseView = 2;
                    }
                }
            }
            return new int[]{id, caseView};
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(int id, int i);
    }

}
