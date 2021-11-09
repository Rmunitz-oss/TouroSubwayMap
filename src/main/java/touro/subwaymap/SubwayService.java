package touro.subwaymap;

import java.io.IOException;
import java.util.*;
import static java.util.Collections.*;
import static touro.subwaymap.SubwayStations.*;

public class SubwayService {
    private final SubwayStations subwayStations;
    private Station start;
    private Station end;
    private ArrayList<Station> unvisitedStations = new ArrayList<>();
    private Station closestStation;

    public SubwayService () throws IOException {
        JSONReader jsonReader = new JSONReader();
        subwayStations = jsonReader.readSubwayStationJSON();
        Map<String, ArrayList<Integer>> lineStationMap = jsonReader.readSubwayLinesJSON();
        SubwayDataManager subwayDataManager = new SubwayDataManager();
        subwayDataManager.processSubwayData(subwayStations, lineStationMap);
    }

    public SubwayStations getSubwayStations(){
        return this.subwayStations;
    }

    public ArrayList<Station> getSubwayRoute (double [] startLocation, double [] endLocation) {
        start = findNearestStation(startLocation[0],startLocation[1]);
        end = findNearestStation(endLocation[0],endLocation[1]);
        return findShortestRoute();
    }


    private Station findNearestStation(double longitude, double latitude) {
        return subwayStations.stations.stream().
                min(Comparator.comparingDouble(station -> station.geometry.getDistance(longitude,latitude)))
                .get();
    }

    private ArrayList<Station> findShortestRoute() {
        for (Station station : subwayStations.stations) {
            station.properties.distance = Integer.MAX_VALUE;
            unvisitedStations.add(station);
        }
        start.properties.distance = 0;
        closestStation = start;
        while (unvisitedStations.contains(end)){
            closestStation = getClosestStation();
            unvisitedStations.remove(closestStation);
            if (!closestStation.equals(end)){
                ArrayList<Station> adjacentStations = closestStation.properties.adjacentStations;
                for (Station adjacentStation : adjacentStations){
                    if (unvisitedStations.contains(adjacentStation)){
                        int length = 1;
                        int altDistance = closestStation.properties.distance + length;
                        if (altDistance <  adjacentStation.properties.distance){
                            adjacentStation.properties.distance = altDistance;
                            adjacentStation.properties.previousStation = closestStation;
                        }
                    }
                }
            }
        }
        return getStationPath();
    }

    private Station getClosestStation() {
        int minDistance = Integer.MAX_VALUE;
        for (Station unvisitedStation : unvisitedStations) {
            if (unvisitedStation.properties.distance < minDistance) {
                closestStation = unvisitedStation;
                minDistance = unvisitedStation.properties.distance;
            }
        }
        return closestStation;
    }

    private ArrayList<Station> getStationPath() {
        ArrayList <Station> stationPath = new ArrayList<>();
        Station currentStation = end;
        stationPath.add(currentStation);
        while (!currentStation.equals(start)){
            stationPath.add(currentStation.properties.previousStation);
            currentStation = currentStation.properties.previousStation;
        }
        reverse(stationPath);
        return stationPath;
    }
}
