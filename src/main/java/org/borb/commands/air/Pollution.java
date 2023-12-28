package org.borb.commands.air;

import java.util.Date;

public class Pollution{
    public Date ts;
    public int aqius;
    public String mainus;
    public int aqicn;
    public String maincn;

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public int getAqius() {
        return aqius;
    }

    public void setAqius(int aqius) {
        this.aqius = aqius;
    }

    public String getMainus() {
        return mainus;
    }

    public void setMainus(String mainus) {
        this.mainus = mainus;
    }

    public int getAqicn() {
        return aqicn;
    }

    public void setAqicn(int aqicn) {
        this.aqicn = aqicn;
    }

    public String getMaincn() {
        return maincn;
    }

    public void setMaincn(String maincn) {
        this.maincn = maincn;
    }

    public Pollution() {
    }

    public Pollution(Date ts, int aqius, String mainus, int aqicn, String maincn) {
        this.ts = ts;
        this.aqius = aqius;
        this.mainus = mainus;
        this.aqicn = aqicn;
        this.maincn = maincn;
    }

    @Override
    public String toString() {
        return "Pollution{" +
                "ts=" + ts +
                ", aqius=" + aqius +
                ", mainus='" + mainus + '\'' +
                ", aqicn=" + aqicn +
                ", maincn='" + maincn + '\'' +
                '}';
    }
}
