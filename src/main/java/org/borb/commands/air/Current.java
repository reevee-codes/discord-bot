package org.borb.commands.air;

public class Current {
    public Pollution pollution;
    public Weather weather;

    public Current(Pollution pollution, Weather weather) {
        this.pollution = pollution;
        this.weather = weather;
    }

    public Current() {
    }

    @Override
    public String toString() {
        return "pollution='" + pollution + '\'' +
                ", weather='" + weather + '\'';
    }
}