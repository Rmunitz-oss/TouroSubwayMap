package touro.subwaymap;

import java.io.IOException;
import java.util.*;
import static java.util.Collections.*;
import static touro.subwaymap.SubwayStations.*;

public class SubwayService {
    //clean, which belong in methods? public vs private?
    private SubwayStations subwayStations;
    private Station start;
    private Station end;
    private ArrayList<Station> unvisitedStations = new ArrayList<>();
    private Station closestStation;
    private final int length = 1;

    //where should this kind of setup be?
    // if not here,getSubwayRoute would need a SubwayStations object as a param? (not intuitive)
    //create constructor for SubwayService that takes SubwayStation arg?
    public void initialize() throws IOException {
        JSONReader jsonReader = new JSONReader();
        subwayStations = jsonReader.readSubwayStationJSON();
        Map<String, ArrayList<Integer>> lineStationMap = jsonReader.readSubwayLinesJSON();
        SubwayDataManager subwayDataManager = new SubwayDataManager();
        subwayDataManager.processSubwayData(subwayStations, lineStationMap);
    }

    public ArrayList<Station> getSubwayRoute (double [] startLocation, double [] endLocation) {//what params would really be passed?
        start = findNearestStation(startLocation[0],startLocation[1]);
        end = findNearestStation(endLocation[0],endLocation[1]);
        ArrayList<Station> shortestRoute = findShortestRoute();
        return shortestRoute; //append final station? rewrite loop to include end/ exclude start?
    }

    private Station findNearestStation(double longitude, double latitude) {
        Station closestStation = subwayStations.stations.stream().
                min(Comparator.comparingDouble(station -> station.geometry.getDistance(longitude,latitude)))
                .get();
        return closestStation;
    }

    private ArrayList<Station> findShortestRoute() {
        for (Station station : subwayStations.stations) {
            station.properties.distance = Integer.MAX_VALUE;
            unvisitedStations.add(station);
        }
        start.properties.distance = 0;
        while (unvisitedStations.contains(end)){
            closestStation = getClosestStation();
            unvisitedStations.remove(closestStation);
            if (!closestStation.equals(end)){
                ArrayList<Station> adjacentStations = closestStation.properties.adjacentStations;
                for (Station adjacentStation : adjacentStations){
                    if (unvisitedStations.contains(adjacentStation)){
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
        while (!currentStation.equals(start)){
            stationPath.add(currentStation.properties.previousStation);
            currentStation = currentStation.properties.previousStation;
        }
        reverse(stationPath);
        return stationPath;
    }
}
