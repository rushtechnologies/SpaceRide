package application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import application.android.com.rushtechnologies.spaceride.R;

public class StatsRecyclerViewAdapter extends RecyclerView.Adapter<StatsRecyclerViewAdapter.ViewHolder>{
    private List<String> areas;
    private List<Integer> count;
    private int layout;
    private StatsRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public StatsRecyclerViewAdapter(List<String> areas, List<Integer> count, int layout, StatsRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.areas = areas;
        this.count = count;
        this.layout = layout;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public StatsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        StatsRecyclerViewAdapter.ViewHolder viewHolder = new StatsRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StatsRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(areas.get(i), count.get(i), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return areas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name_area, count_area;

        public ViewHolder(View view) {
            super(view);
            this.name_area = view.findViewById(R.id.name_area);
            this.count_area = view.findViewById(R.id.count_area);
        }

        public void bind(final String area, final int count, final StatsRecyclerViewAdapter.OnItemClickListener onItemClickListener){
            this.name_area.setText(area);
            this.count_area.setText("" + count);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(area, count, getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickListener {
        void OnItemClick(String area, int count, int position);
    }

}
