package touro.subwaymap;

import touro.subwaymap.SubwayStations.Features.Properties;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

//rename class?

public class StationLineConnections {
    public void connectSubwayData(SubwayStations subwayStations, Map <String,ArrayList<String>> lineStationMap) {
        for (int index =0; index < subwayStations.features.size(); index++ ){
            Properties station = subwayStations.features.get(index).properties;
            station.connectingLines = station.line.split("-");
            for (String line : station.connectingLines) {
                for (String lineKey : lineStationMap.keySet()){
                    if(lineKey.equals(line)){
                        station.connectingStationIDs.addAll(lineStationMap.get(lineKey));
                    }
                }
            }
            //remove duplicates
            station.connectingStationIDs = (ArrayList<String>) station.connectingStationIDs.stream().distinct().collect(Collectors.toList());
        }

    }
// is this needed if this information can be accessed directly through a subwayStations object?
    public ArrayList <String> getConnectingStations(SubwayStations subwayStations, int stationID){
        for (int index = 0; index < subwayStations.features.size(); index++ ){
            Properties stationProperties = subwayStations.features.get(index).properties;
            if (stationProperties.objectid == stationID){
                return stationProperties.connectingStationIDs;
            }
        }
        return new ArrayList<>(); // return empty list rather than null
    }
}
