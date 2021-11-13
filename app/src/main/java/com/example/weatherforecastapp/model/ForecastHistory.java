package com.example.weatherforecastapp.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForecastHistory {
    private String id;
    private String cityName;
    private ForecastType forecastType;
    private Date searchDate;
}
