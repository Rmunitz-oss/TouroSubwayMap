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
            return properties.equals(station.properties) && geometry.equals(station.geometry);
        }

        @Override
        public int hashCode() {
            return Objects.hash(properties, geometry);
        }

        static class Properties {
            String name;
            int objectid;
            String line;
            ArrayList<Station> adjacentStations = new ArrayList<>();

            public String [] getConnectingLines(){
                return line.split("-");
            }
        }

        static class Geometry {
            List<Double> coordinates;
        }
    }
}