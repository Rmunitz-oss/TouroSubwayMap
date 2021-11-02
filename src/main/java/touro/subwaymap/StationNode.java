package touro.subwaymap;

import java.util.ArrayList;
import java.util.Objects;

import static touro.subwaymap.SubwayStations.*;

public class StationNode {
    Station station;
    boolean unvisited;
    int distance;
    StationNode previousStation;
    ArrayList<Station> adjacentStations;


    public StationNode (Station station){
        this.station = station;
        unvisited = true;
        distance = Integer.MAX_VALUE;
        previousStation = null;
        adjacentStations = station.properties.adjacentStations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationNode that = (StationNode) o;
        return unvisited == that.unvisited && distance == that.distance && station.equals(that.station) && previousStation.equals(that.previousStation) && adjacentStationNodes.equals(that.adjacentStationNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(station, unvisited, distance, previousStation, adjacentStationNodes);
    }
}
