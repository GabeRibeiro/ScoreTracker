package com.example.scoretracker.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.scoretracker.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Estatisticas extends Fragment {
    private int[] possebola = {56, 44};
    private String[] clube = {"Tondela", "Paços Ferreira"};
    PieChart pieChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.estatistica_jogo_tondela, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pieChart = (PieChart) view.findViewById(R.id.possebola);
        pieChart.setRotationEnabled(false);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawHoleEnabled(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        addDataSet(pieChart);
    }

    private void addDataSet(PieChart pieChart) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for (int i = 0; i < possebola.length; i++) {
            yEntrys.add(new PieEntry(possebola[i], i));
        }

        for (int i = 0; i < clube.length; i++) {
            xEntrys.add(clube[i]);
        }
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, null);
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> color = new ArrayList<>();
        color.add(Color.GREEN);
        color.add(Color.YELLOW);

        pieDataSet.setColors(color);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter(pieChart));

        pieChart.setData(pieData);
        pieChart.setUsePercentValues(true);
        pieChart.invalidate();

    }

    public class PercentFormatter extends ValueFormatter {

        public DecimalFormat mFormat;
        private PieChart pieChart;

        public PercentFormatter() {
            mFormat = new DecimalFormat("###,###,###");
        }

        // Can be used to remove percent signs if the chart isn't in percent mode

        public PercentFormatter(PieChart pieChart) {
            this();
            this.pieChart = pieChart;
        }

        @Override
        public String getFormattedValue(float value) {
            return mFormat.format(value) + " %";
        }


    }
}
