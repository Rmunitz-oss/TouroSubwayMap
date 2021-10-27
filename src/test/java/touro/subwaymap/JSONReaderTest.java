package touro.subwaymap;

import org.junit.jupiter.api.Test;
import touro.subwaymap.SubwayStations.Station;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONReaderTest {
    public JSONReader jsonReader;

    @Test
    public void readJson() throws IOException {
        //given
        jsonReader = new JSONReader();

        //when
        SubwayStations subwayStations = jsonReader.readSubwayStationJSON();
        Station station = subwayStations.stations.get(0);

        //then
        assertEquals("Astor Pl", station.properties.name);
        assertEquals(1, station.properties.objectid);
        assertEquals("4-6-6 Express", station.properties.line);
        assertEquals(-73.99106999861966, station.geometry.coordinates.get(0));
        assertEquals(40.73005400028978, station.geometry.coordinates.get(1));

    }

    @Test
    public void readSubwayLines() throws IOException{
        //given
        jsonReader = new JSONReader();

        //when
        Map <String,ArrayList<Integer>> subwayLines = jsonReader.readSubwayLinesJSON();

        //then
        for(String lineKey : subwayLines.keySet()){
            if (lineKey.equals("A")){
                assertEquals(55, subwayLines.get(lineKey).get(0));
            }
            if(lineKey.equals("6 Express")){
                assertEquals(29, subwayLines.get(lineKey).get(0));
            }
        }
    }
}
