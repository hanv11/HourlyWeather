package sample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather {
    private String hour;
    private double temperature;
    private String desc;

    public Weather(String hour, double temperature, String desc) {
        this.hour = hour;
        this.temperature = temperature;
        this.desc = desc;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
