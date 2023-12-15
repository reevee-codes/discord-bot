package org.borb.commands.newyorktimes;

import java.util.ArrayList;
import java.util.Date;

public class Root{
    public String status;
    public String copyright;
    public String section;
    public Date last_updated;
    public int num_results;
    public ArrayList<Result> results;
}
