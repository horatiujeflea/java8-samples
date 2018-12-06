package com.horatiuj.sample.gps.function;

import com.horatiuj.sample.gps.model.GpsPoint;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.function.Predicate;

public class IsPointValid implements Predicate<GpsPoint> {

    private static final String LATITUDE_PATTERN="^([+\\-])?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
    private static final String LONGITUDE_PATTERN="^([+\\-])?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";
    private final DecimalFormat df;

    {
        df = new DecimalFormat("#.######");
        df.setRoundingMode(RoundingMode.UP);
    }

    @Override
    public boolean test(GpsPoint gpsPoint) {
        Objects.requireNonNull(gpsPoint);

        boolean isAccuracyValid = gpsPoint.accuracy() == null || (gpsPoint.accuracy() != null && gpsPoint.accuracy() < 10);
        return isAccuracyValid &&
                df.format(gpsPoint.lat()).matches(LATITUDE_PATTERN) &&
                df.format(gpsPoint.lon()).matches(LONGITUDE_PATTERN);
    }
}
