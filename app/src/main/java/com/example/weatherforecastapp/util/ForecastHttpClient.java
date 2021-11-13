package com.example.weatherforecastapp.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ForecastHttpClient {
    private static final String BASE_URL_TODAY_FORECAST = "http://api.openweathermap.org/data/2.5/weather";
    private static final String BASE_URL_FIVE_DAY_FORECAST = "http://api.openweathermap.org/data/2.5/forecast";
    private static final String APP_ID = "39a0e214163b682fce539c7b687a679d";
    private static final String UNITS = "metric";

    private static final AsyncHttpClient client = new AsyncHttpClient();

    public static void getForecast(String cityName, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = buildRequestParams(cityName);
        client.get(BASE_URL_TODAY_FORECAST, params, responseHandler);
    }

    public static void getFiveDayForecast(String cityName, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = buildRequestParams(cityName);
        client.get(BASE_URL_FIVE_DAY_FORECAST, params, responseHandler);
    }


    private static RequestParams buildRequestParams(String cityName) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("q", cityName);
        requestParams.put("appid", APP_ID);
        requestParams.put("units", UNITS);
        return requestParams;
    }
}
