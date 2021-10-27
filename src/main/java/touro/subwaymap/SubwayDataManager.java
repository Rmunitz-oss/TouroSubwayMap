package touro.subwaymap;

import java.util.*;
import java.util.stream.Collectors;

//to do: add station objects, versus station IDs
public class SubwayDataManager {
    public void processSubwayData(SubwayStations.Station station, Map <String,ArrayList<Integer>> lineStationMap, int stationID) {
            station.properties.connectingLines = station.properties.line.split("-");
            for (String line : station.properties.connectingLines) {
                for (String lineKey : lineStationMap.keySet()){
                    if(lineKey.equals(line)){
                        ArrayList<Integer> connectingStationIDs = lineStationMap.get(lineKey);
                        int ix = connectingStationIDs.indexOf(stationID);
                        if (ix > 0){
                            station.properties.adjacentStationIDs.add(connectingStationIDs.get(ix-1));
                        }
                        if (ix < connectingStationIDs.size()){
                            station.properties.adjacentStationIDs.add(connectingStationIDs.get(ix+1));
                        }
                    }
                }
            }
            //remove duplicates
            station.properties.adjacentStationIDs = (ArrayList<Integer>) station.properties.adjacentStationIDs.stream().distinct().collect(Collectors.toList());
    }
}

