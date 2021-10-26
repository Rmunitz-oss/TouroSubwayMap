package touro.subwaymap;

import org.junit.jupiter.api.Test;
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

        //then
        assertEquals("Astor Pl", subwayStations.features.get(0).properties.name);
        assertEquals(1, subwayStations.features.get(0).properties.objectid);
        assertEquals("4-6-6 Express", subwayStations.features.get(0).properties.line);
        assertEquals(-73.99106999861966, subwayStations.features.get(0).geometry.coordinates.get(0));
        assertEquals(40.73005400028978, subwayStations.features.get(0).geometry.coordinates.get(1));

    }

    @Test
    public void readSubwayLines() throws IOException{
        //given
        jsonReader = new JSONReader();

        //when
        Map <String,ArrayList<String>> subwayLines = jsonReader.readSubwayLinesJSON();

        //then
        for(String lineKey : subwayLines.keySet()){
            if (lineKey.equals("A")){
                assertEquals("55", subwayLines.get(lineKey).get(0));
            }
            if(lineKey.equals("6 Express")){
                assertEquals("29", subwayLines.get(lineKey).get(0));
            }
        }
    }
}
