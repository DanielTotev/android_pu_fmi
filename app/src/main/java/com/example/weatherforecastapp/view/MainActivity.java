package com.example.weatherforecastapp.view;

import static com.example.weatherforecastapp.util.ForecastFactory.createForecastFromApiResponse;
import static com.example.weatherforecastapp.util.ForecastHttpClient.getForecast;
import static com.example.weatherforecastapp.util.ImageLoader.loadImage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.weatherforecastapp.R;
import com.example.weatherforecastapp.model.Forecast;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import lombok.SneakyThrows;

public class MainActivity extends BaseActivity {
    private EditText cityNameEditText;
    private Button searchForecastButton;
    private ImageView forecastIconImageView;
    private TextView temperatureTextView;
    private TextView weatherDescriptionTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViewItems();
        handleSearchForecastButtonClick();
        hideProgressBar();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void handleSearchForecastButtonClick() {
        searchForecastButton.setOnClickListener(v -> {
            showProgressBar();
            String cityName = cityNameEditText.getText().toString();
            getForecast(cityName, new ForecastJsonResponseHandler());
        });
    }

    private void bindViewItems() {
        cityNameEditText = findViewById(R.id.etCityName);
        searchForecastButton = findViewById(R.id.btnSearch);
        forecastIconImageView = findViewById(R.id.ivListWeatherIcon);
        temperatureTextView = findViewById(R.id.tvTemperature);
        progressBar = findViewById(R.id.progressBar1);
        weatherDescriptionTextView = findViewById(R.id.tvWeatherDescription);
    }

    private void updateTemperatureTextValue(int temperature) {
        temperatureTextView.setText(String.format("%d°C", temperature));
    }

    private void updateForecastIcon(String iconName) {
        Bitmap bitmap = loadImage(iconName);
        forecastIconImageView.setImageBitmap(bitmap);
    }

    private void updateWeatherDescription(String weatherDescription) {
        weatherDescriptionTextView.setText(weatherDescription);
    }

    private class ForecastJsonResponseHandler extends JsonHttpResponseHandler {
        @SneakyThrows
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            Forecast forecast = createForecastFromApiResponse(response);
            updateTemperatureTextValue(forecast.getTemperature());
            updateForecastIcon(forecast.getIcon());
            updateWeatherDescription(forecast.getWeatherDescription());
            hideProgressBar();
        }
    }
}