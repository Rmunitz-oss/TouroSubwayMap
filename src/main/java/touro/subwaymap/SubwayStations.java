package touro.subwaymap;

import com.google.gson.annotations.SerializedName;
import java.util.*;

public class SubwayStations {

    @SerializedName("features")
    List<Station> stations;

    static class Station {
        Properties properties;
        Geometry geometry;

        static class Properties {
            String name;
            int objectid;
            String line;
            String [] connectingLines;
            ArrayList<Integer> adjacentStationIDs = new ArrayList<>();
        }
        static class Geometry {
            List<Double> coordinates;
        }
    }
}