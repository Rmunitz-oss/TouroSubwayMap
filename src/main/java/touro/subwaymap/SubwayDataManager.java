package touro.subwaymap;

import java.util.*;
import java.util.stream.Collectors;
import static touro.subwaymap.SubwayStations.*;

public class SubwayDataManager {
    Map<Integer,Station> idToStationMap = new HashMap<>();

    public void processSubwayData(SubwayStations subwayStations, Map <String,ArrayList<Integer>> lineStationMap) {
        for (Station station : subwayStations.stations){
            idToStationMap.put(station.properties.objectid,station); // extract method?
        }
        for (Station station : subwayStations.stations){
            for (String line : station.properties.getConnectingLines()) {
                for (String lineKey : lineStationMap.keySet()){
                    if(lineKey.equals(line)){
                        ArrayList<Integer> connectingStationIDs = lineStationMap.get(lineKey);
                        int ix = connectingStationIDs.indexOf(station.properties.objectid);
                        if (ix > 0){
                            int precedingStationID = connectingStationIDs.get(ix-1);
                            station.properties.adjacentStations.add(idToStationMap.get(precedingStationID));
                        }
                        if (ix < connectingStationIDs.size() - 1){
                            int followingStationID = connectingStationIDs.get(ix+1);
                            station.properties.adjacentStations.add(idToStationMap.get(followingStationID));
                        }
                    }
                }
            }
            //remove duplicates
            station.properties.adjacentStations = (ArrayList<Station>) station.properties.adjacentStations.stream().distinct().collect(Collectors.toList());
        }
    }
}

