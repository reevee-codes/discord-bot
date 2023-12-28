package org.borb.commands.air;

public class WeatherResponse {

    public WeatherResponse() {
    }

    public String status;
    public Current current;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}
