package touro.subwaymap;

import java.util.List;

public class Stations {
    List<Features> features;
    List<Stations> connections;
    //TO DO: each station will have a list of connecting stations

    static class Features {
        Properties properties;
        Geometry geometry;

        static class Properties {
            String name;
            int objectid;
        }

        static class Geometry {
            List<Double> coordinates;
        }
    }
}