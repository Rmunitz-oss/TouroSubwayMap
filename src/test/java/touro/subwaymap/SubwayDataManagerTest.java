package touro.subwaymap;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static touro.subwaymap.SubwayStations.*;

public class SubwayDataManagerTest {
    private SubwayDataManager subwayDataManager;
    private SubwayStations subwayStations;
    private Station station;
    private Station precedingStation;
    private Station followingStation;
    private Map<String,ArrayList<Integer>> lineStationMap;

    @Test
    public void connectSubwayData() throws IOException {
        //given
        givenStationLineConnections();

        //when
        subwayDataManager.processSubwayData(subwayStations,lineStationMap);

        //then
        //test hash map
        assertEquals("4",station.properties.getConnectingLines()[0]);
        assertEquals(precedingStation, station.properties.adjacentStations.get(0));
        assertEquals(followingStation, station.properties.adjacentStations.get(1));
    }

    public void givenStationLineConnections() throws IOException {
        JSONReader jsonReader = new JSONReader();
        subwayStations = jsonReader.readSubwayStationJSON();
        station = subwayStations.stations.get(0);
        precedingStation = subwayStations.stations.get(456);
        followingStation = subwayStations.stations.get(104);
        lineStationMap = jsonReader.readSubwayLinesJSON();
        subwayDataManager = new SubwayDataManager();
    }
}
