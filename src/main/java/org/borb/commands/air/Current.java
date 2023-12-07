package org.borb.commands.air;

public class Current {
    public Pollution pollution;
    public Weather weather;

    @Override
    public String toString() {
        return "pollution='" + pollution + '\'' +
                ", weather='" + weather + '\'';
    }
}