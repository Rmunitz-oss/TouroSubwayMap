package touro.subwaymap;

import java.util.*;
import java.util.stream.Collectors;
import static touro.subwaymap.SubwayStations.*;

//to do: add station objects, versus station IDs
public class SubwayDataManager {
    Map<Integer,Station> idToStationMap = new HashMap<>();
    public void processSubwayData(SubwayStations subwayStations, Map <String,ArrayList<Integer>> lineStationMap, int stationID) {
        for (Station station : subwayStations.stations){
            idToStationMap.put(station.properties.objectid,station); // extract method?
        }
        for (Station station : subwayStations.stations){
            station.properties.connectingLines = station.properties.line.split("-");
            for (String line : station.properties.connectingLines) {
                for (String lineKey : lineStationMap.keySet()){
                    if(lineKey.equals(line)){
                        ArrayList<Integer> connectingStationIDs = lineStationMap.get(lineKey);
                        int ix = connectingStationIDs.indexOf(stationID);
                        if (ix > 0){
                            int adjacentStationID = connectingStationIDs.get(ix-1);
                            station.properties.adjacentStations.add(idToStationMap.get(adjacentStationID));
                            //station.properties.adjacentStationIDs.add(connectingStationIDs.get(ix-1));
                        }
                        if (ix < connectingStationIDs.size()){
                            int adjacentStationID = connectingStationIDs.get(ix+1);
                            station.properties.adjacentStations.add(idToStationMap.get(adjacentStationID));
                            //station.properties.adjacentStationIDs.add(connectingStationIDs.get(ix+1));
                        }
                    }
                }
            }
            //remove duplicates
            station.properties.adjacentStations = (ArrayList<Station>) station.properties.adjacentStations.stream().distinct().collect(Collectors.toList());
        }
    }
    /* where to put this method?
    public void createHashMap(SubwayStations subwayStations){
        for (Station station : subwayStations.stations){
            int key = station.properties.objectid;
            Station value = station;
            idToStationMap.put(key,value);
        }
    }
    \
     */
}

