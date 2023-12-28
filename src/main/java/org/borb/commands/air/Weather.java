package org.borb.commands.air;

import java.util.Date;

public class Weather {

    public Weather(Date ts, int tp, int pr, int hu, double ws, int wd, String ic) {
        this.ts = ts;
        this.tp = tp;
        this.pr = pr;
        this.hu = hu;
        this.ws = ws;
        this.wd = wd;
        this.ic = ic;
    }

    public Weather() {
    }

    public Date ts;
    public int tp;
    public int pr;
    public int hu;
    public double ws;
    public int wd;
    public String ic;

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public int getPr() {
        return pr;
    }

    public void setPr(int pr) {
        this.pr = pr;
    }

    public int getHu() {
        return hu;
    }

    public void setHu(int hu) {
        this.hu = hu;
    }

    public double getWs() {
        return ws;
    }

    public void setWs(double ws) {
        this.ws = ws;
    }

    public int getWd() {
        return wd;
    }

    public void setWd(int wd) {
        this.wd = wd;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "ts=" + ts +
                ", tp=" + tp +
                ", pr=" + pr +
                ", hu=" + hu +
                ", ws=" + ws +
                ", wd=" + wd +
                ", ic='" + ic + '\'' +
                '}';
    }
}