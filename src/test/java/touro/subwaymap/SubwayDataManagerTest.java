package touro.subwaymap;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SubwayDataManagerTest {
    private SubwayDataManager connections;
    private SubwayStations subwayStations;
    private Map<String,ArrayList<String>> lineStationMap;

    @Test
    public void connectSubwayData() throws IOException {
        //given
        givenStationLineConnections();

        //when
        connections.processSubwayData(subwayStations,lineStationMap);

        //then
        assertEquals("4", subwayStations.features.get(0).properties.connectingLines[0]);
        assertEquals("213", subwayStations.features.get(0).properties.connectingStationIDs.get(0));
        assertEquals("46", subwayStations.features.get(0).properties.connectingStationIDs.get(1));
    }

    @Test
    public void getConnectingStations() throws IOException{
        //given
        givenStationLineConnections();
        connections.processSubwayData(subwayStations,lineStationMap);

        //when
        ArrayList<String> connectingStations = connections.getConnectingStations(subwayStations,1);

        //then
        assertFalse(subwayStations.features.get(0).properties.connectingStationIDs.isEmpty());
        assertEquals(connectingStations, subwayStations.features.get(0).properties.connectingStationIDs);
    }

    public void givenStationLineConnections() throws IOException {
        connections = new SubwayDataManager();
        JSONReader jsonReader = new JSONReader();
        subwayStations = jsonReader.readSubwayStationJSON();
        lineStationMap = jsonReader.readSubwayLinesJSON();
    }
}