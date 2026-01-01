package com.example.gpsfleet.util;

import org.springframework.stereotype.Service;

@Service
public class DistanceCalcService {
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        if(lat2!=0 || lon2 !=0 ||lat1!=0 || lon1 !=0){
        final int R = 6371;
        double Dlat = Math.toRadians(lat2 - lat1);
        double Dlon = Math.toRadians(lon2 - lon1);

        double rLat1 = Math.toRadians(lat1);
        double rLat2 = Math.toRadians(lat2);
//    a = sin²(Δlat/2) + cos(lat1) * cos(lat2) * sin²(Δlon/2)
        double a = Math.sin(Dlat / 2) * Math.sin(Dlat / 2) +
                Math.cos(rLat1) * Math.cos(rLat2) *
                        Math.sin(Dlon / 2) * Math.sin(Dlon / 2);
        // c = 2 * atan2(√a, √(1−a))
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        // c * R
        double km = R * c;
            System.out.println(R*c);
        return km * 1000;

    }
        return 0.0;
        }
}
