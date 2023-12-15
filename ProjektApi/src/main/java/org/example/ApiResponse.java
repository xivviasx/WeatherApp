package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    private Main main;
    private String name;
    public Main getMain() {
        return main;
    }
    public String getName() {
        return name;
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        private double temp;
        private int pressure;
        private int humidity;

        public double getTemp() {
            return temp;
        }
        public int getPressure() {
            return pressure;
        }
        public int getHumidity() {
            return humidity;
        }
    }
}
