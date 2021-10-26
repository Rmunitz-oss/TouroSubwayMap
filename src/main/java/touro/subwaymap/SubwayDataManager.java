package touro.subwaymap;

import touro.subwaymap.SubwayStations.Features.Properties;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class SubwayDataManager {
    public void processSubwayData(SubwayStations subwayStations, Map <String,ArrayList<String>> lineStationMap) {
        for (int index =0; index < subwayStations.features.size(); index++ ){
            Properties stationProperties = subwayStations.features.get(index).properties;
            stationProperties.connectingLines = stationProperties.line.split("-");
            for (String line : stationProperties.connectingLines) {
                for (String lineKey : lineStationMap.keySet()){
                    if(lineKey.equals(line)){
                        stationProperties.connectingStationIDs.addAll(lineStationMap.get(lineKey));
                    }
                }
            }
            //remove duplicates
            stationProperties.connectingStationIDs = (ArrayList<String>) stationProperties.connectingStationIDs.stream().distinct().collect(Collectors.toList());
        }

    }
    // is this needed if this information can be accessed directly through the subwayStations object?
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
