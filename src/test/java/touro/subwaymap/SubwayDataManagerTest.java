package touro.subwaymap;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class SubwayDataManagerTest {
    private SubwayDataManager subwayDataManager;
    private SubwayStations.Station station;
    private Map<String,ArrayList<Integer>> lineStationMap;

    @Test
    public void connectSubwayData() throws IOException {
        //given
        givenStationLineConnections();

        //when
        subwayDataManager.processSubwayData(station,lineStationMap,1);

        //then
        assertEquals("4",station.properties.connectingLines[0]);
        assertEquals(457,station.properties.adjacentStationIDs.get(0));
        assertEquals(105, station.properties.adjacentStationIDs.get(1));
        //test with first or last station in a line to confirm

    }

    public void givenStationLineConnections() throws IOException {
        JSONReader jsonReader = new JSONReader();
        SubwayStations subwayStations = jsonReader.readSubwayStationJSON();
        station = subwayStations.stations.get(0);
        lineStationMap = jsonReader.readSubwayLinesJSON();
        subwayDataManager = new SubwayDataManager();
    }
}
