package com.example.weatherforecastapp.view;

import static com.example.weatherforecastapp.util.ForecastFactory.createForecastsFromApiResponse;
import static com.example.weatherforecastapp.util.ForecastHttpClient.getFiveDayForecast;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.weatherforecastapp.R;
import com.example.weatherforecastapp.model.Forecast;
import com.example.weatherforecastapp.model.MultiDayForecast;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import lombok.SneakyThrows;

public class FiveDayForecastActivity extends BaseActivity {
    private EditText cityNameEditText;
    private Button searchButton;
    private ProgressBar progressBar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_day_forecast);
        bindViewItems();
        handleSearchButtonClick();
        hideProgressBar();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void handleSearchButtonClick() {
        searchButton.setOnClickListener(v -> {
            showProgressBar();
            String cityName = cityNameEditText.getText().toString();
            getFiveDayForecast(cityName , new FiveDayForecastJsonResponseHandler());
        });
    }

    private void bindViewItems() {
        cityNameEditText = findViewById(R.id.etCityNameFiveDayForecast);
        searchButton = findViewById(R.id.btnFiveDayForecastSearch);
        progressBar = findViewById(R.id.progressBar);
        listView = findViewById(R.id.lvFiveDayForecast);
    }

    private class FiveDayForecastJsonResponseHandler extends JsonHttpResponseHandler {
        @SneakyThrows
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            List<MultiDayForecast> forecasts = createForecastsFromApiResponse(response);
            setListViewAdapter(forecasts);
            hideProgressBar();
        }
    }

    private void setListViewAdapter(List<MultiDayForecast> forecasts) {
        ArrayAdapter<MultiDayForecast> multiDayForecastArrayAdapter =
                new WeatherForecastListAdapter(this, R.id.list_item, forecasts);
        listView.setAdapter(multiDayForecastArrayAdapter);
    }
}