package com.example.weatherforecastapp.util;

import com.example.weatherforecastapp.model.Forecast;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ForecastFactory {
    public static Forecast createForecastFromApiResponse(JSONObject jsonObject) throws JSONException {
        Forecast forecast = new Forecast();
        forecast.setTemperature(extractTemperature(jsonObject));
        forecast.setIcon(extractIcon(jsonObject));
        return forecast;
    }

    private static String extractIcon(JSONObject jsonObject) throws JSONException {
        return jsonObject
                .getJSONArray("weather")
                .getJSONObject(0)
                .getString("icon");
    }

    private static int extractTemperature(JSONObject jsonObject) throws JSONException {
        double temperature =
                jsonObject.getJSONObject("main")
                .getDouble("temp");
        return (int) Math.round(temperature);
    }
}
