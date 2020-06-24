package com.etysoft.ban;

import java.util.concurrent.TimeUnit;

public class TimeConvert {

    public static long msToMin(long milliseconds)
    {
        return TimeUnit.MILLISECONDS.toMinutes(milliseconds);
    }
}
