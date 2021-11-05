package touro.subwaymap;

import com.google.gson.annotations.SerializedName;
import java.util.*;

public class SubwayStations {

    @SerializedName("features")
    List<Station> stations;

    static class Station {
        Properties properties;
        Geometry geometry;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Station station = (Station) o;
            return Objects.equals(properties, station.properties) && Objects.equals(geometry, station.geometry);
        }

        @Override
        public int hashCode() {
            return Objects.hash(properties, geometry);
        }


        static class Properties {
            String name;
            int objectid;
            String line;
            int distance;
            ArrayList<Station> adjacentStations = new ArrayList<>();
            Station previousStation;

            public String [] getConnectingLines(){
                return line.split("-");
            }
        }

        static class Geometry {
            List<Double> coordinates;

            /**
             * uses distance formula to calculate
             * distance between station and provided location
             * @param longitude GPS coordinate
             * @param latitude GPS coordinate
             * @return double distance
             */
            public double getDistance(double longitude, double latitude){
                double x1 = this.coordinates.get(0);
                double y1 = this.coordinates.get(1);
                return Math.sqrt(Math.pow((longitude-x1),2) + Math.pow((latitude-y1),2));
            }
        }
    }
}