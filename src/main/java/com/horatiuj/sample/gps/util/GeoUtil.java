package com.horatiuj.sample.gps.util;

import com.horatiuj.sample.gps.model.GpsPoint;

public class GeoUtil {
    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. Uses Haversine method as its base.
     *
     * @return Distance in Meters
     */
    public static double distance(GpsPoint point1, GpsPoint point2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(point2.lat() - point1.lat());
        double lonDistance = Math.toRadians(point2.lon() - point1.lon());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(point1.lat())) * Math.cos(Math.toRadians(point2.lat()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}
