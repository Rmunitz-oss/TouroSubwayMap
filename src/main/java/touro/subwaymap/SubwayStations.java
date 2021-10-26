package touro.subwaymap;

import java.util.*;

public class SubwayStations {
    List<Features> features;

    static class Features {
        Properties properties;
        Geometry geometry;

        static class Properties {
            String name;
            int objectid;
            String line;
            String [] connectingLines;
            ArrayList<String> connectingStationIDs = new ArrayList<>(); //allowed to do this here?

        }
        static class Geometry {
            List<Double> coordinates;
        }
    }
}