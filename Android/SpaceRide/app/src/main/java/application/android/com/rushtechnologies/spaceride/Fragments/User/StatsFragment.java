package application.android.com.rushtechnologies.spaceride.Fragments.User;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView.StatsRecyclerViewAdapter;
import application.android.com.rushtechnologies.spaceride.DAO.AccessArea;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpArea;
import application.android.com.rushtechnologies.spaceride.Model.Area;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class StatsFragment extends Fragment {
    private List<String> areas;
    private List<Integer> count;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private OnStatsFragmentInteractionListener callback;
    private User user;
    private Area area;
    private TextView usernameTextView;
    private final AccessArea accessArea = new ImpArea();

    public StatsFragment() {
    }

    @SuppressWarnings("FieldCanBeLocal")
    private PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        getUser(callback.getUser());
        bindElements(view);
        initView();
        return view;
    }

    private void bindElements(View view) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        readArea(user.getU_id());
        usernameTextView = view.findViewById(R.id.username);
        areas = getAreas();
        count = getCount();
        pieChart = view.findViewById(R.id.pieChart);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new StatsRecyclerViewAdapter(areas, count, R.layout.recycler_view_item_grid, new StatsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(String area, int count, int position) {

            }
        });
    }

    private void initView() {
        initPieChart();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        usernameTextView.setText(user.getU_name());
    }

    private void getUser(User user) {
        this.user = user;
    }

    private void initPieChart() {
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT);
        pieChart.setCenterText(generateCenterText());
        pieChart.setCenterTextSize(10f);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT);
        pieChart.setHoleRadius(45f);
        pieChart.setTransparentCircleRadius(50f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setUsePercentValues(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setDrawCenterText(true);
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.animateY(1400, Easing.EaseInOutQuad);
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);
        setData();
    }

    private void setData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(user.getU_wins()));
        entries.add(new PieEntry(user.getU_loses()));
        PieDataSet dataSet = new PieDataSet(entries, "Victorias / Derrotas");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        ArrayList<Integer> colors = new ArrayList<>();
        /*for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);*/
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(this.getResources().getColor(R.color.colorAccent));
        data.setValueTypeface(Typeface.DEFAULT);
        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Victorias\nDerrotas");
        s.setSpan(new RelativeSizeSpan(2f), 0, 9, 0);
        s.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.colorAccent)), 9, s.length(), 0);
        return s;
    }

    private List<String> getAreas() {
        final String[] temas = {"Arte", "Astronomía", "Biología", "Entretenimiento", "Español", "Física", "Geografía", "Historia de México", "Histora Universal", "Inglés", "Matemáticas", "Química", "Tecnología"};
        return new ArrayList<>(Arrays.asList(temas));
    }

    private List<Integer> getCount() {
        final Integer[] counts = {area.getArea_arte(), area.getArea_astro(), area.getArea_bio(), area.getArea_ent(), area.getArea_esp(), area.getArea_fis(), area.getArea_geo(), area.getArea_hmex(), area.getArea_huni(), area.getArea_ing(), area.getArea_mate(), area.getArea_qui(), area.getArea_tec()};
        return new ArrayList<>(Arrays.asList(counts));
    }

    public void readArea(final int id) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    area = accessArea.readArea(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at readArea: " + e.getMessage());
                }
            }
        });
    }

    public interface OnStatsFragmentInteractionListener {
        User getUser();
        void toast(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            callback = (OnStatsFragmentInteractionListener) context;
        } catch(Exception ex) {
            System.out.println("Error at StatsFragment: "+ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

}
