package org.borb.commands.air;

public class Current {
    private Pollution pollution;
    private Weather weather;

    public Current(Pollution pollution, Weather weather) {
        this.pollution = pollution;
        this.weather = weather;
    }

    public Current() {
    }

    public Pollution getPollution() {
        return pollution;
    }

    public void setPollution(Pollution pollution) {
        this.pollution = pollution;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "pollution='" + pollution + '\'' +
                ", weather='" + weather + '\'';
    }
}