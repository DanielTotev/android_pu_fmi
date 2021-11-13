package com.example.weatherforecastapp.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.weatherforecastapp.R;
import com.example.weatherforecastapp.model.ForecastHistory;
import com.example.weatherforecastapp.util.DatabaseHelper;

import java.util.List;

public class HistoryActivity extends BaseActivity {
    private ListView listView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listView = findViewById(R.id.lvHistory);
        databaseHelper = new DatabaseHelper(this);
        fillListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillListView();
    }

    private void fillListView() {
        List<ForecastHistory> historyActivities = databaseHelper.loadAllForecastHistory();
        ArrayAdapter<ForecastHistory> forecastHistoryArrayAdapter =
                new ArrayAdapter<>(this, R.layout.history_row_item, R.id.tvHistoryItem, historyActivities);
        listView.setAdapter(forecastHistoryArrayAdapter);
    }

}