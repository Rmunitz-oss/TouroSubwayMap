package touro.subwaymap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static touro.subwaymap.SubwayStations.*;

public class SubwayMap {
    //map station objects to station nodes?
    private final Map<Station,StationNode> subwayMap = new HashMap<>();
    final int length = 1;

    public void createMap(SubwayStations subwayStations){
        for (Station station : subwayStations.stations){
            subwayMap.put(station, new StationNode(station));
        }
    }

    public void getSubwayRoute(Station.Geometry startCoordinate, Station.Geometry endCoordinate){
        Station startStation = findNearestStation(startCoordinate);
        Station endStation = findNearestStation(endCoordinate);
        getShortestRoute(startStation,endStation);
        //return route
    }
    public Station findNearestStation(Station.Geometry coordinate){
        //TO DO: find nearest subway station based on GPS coordinates
        //distance formula
        //loop through all stations, keep min
        //find more efficient way?
        return null;
    }
    public void getShortestRoute(Station start, Station end){
        ArrayList<StationNode> unvisitedStationNodes = new ArrayList<>(); //nodes or stations? just add needed properties to station object vs having a node as well?
        unvisitedStationNodes.addAll(subwayMap.values());
        StationNode startNode = subwayMap.get(start);
        StationNode endNode = subwayMap.get(end);
        startNode.distance = 0;
        while(!unvisitedStationNodes.isEmpty()){
            //get station with min distance
            StationNode closestStationNode = null;
            int minDistance = Integer.MAX_VALUE;
            for (StationNode stationNode : unvisitedStationNodes){
                int distance = stationNode.distance;
                if (distance < minDistance){
                    minDistance = distance;
                    closestStationNode = stationNode;
                }
            }
            unvisitedStationNodes.remove(closestStationNode); //test
            closestStationNode.unvisited = false;
            if (!closestStationNode.equals(endNode)){//compare station objects not nodes?
                ArrayList<Station> adjacentStations = closestStationNode.adjacentStations;
                for (Station adjacentStation : adjacentStations){
                    StationNode adjacentNode = subwayMap.get(adjacentStation);
                    if (unvisitedStationNodes.contains(adjacentNode)){
                        int altPath = closestStationNode.distance + length;
                        if (altPath < adjacentNode.distance){
                            adjacentNode.distance = altPath;
                            adjacentNode.previousStation = closestStationNode;
                        }
                    }
                }
            }
        }
        Stack <StationNode> stationNodesStack = new Stack<>();
        Station currentStation = end;
        StationNode currentStationNode = endNode;
        while (!currentStation.equals(start)){ //x compare nodes, compare station objects
            stationNodesStack.add(currentStationNode);
            currentStationNode = currentStationNode.previousStation;

        }
    }
}

