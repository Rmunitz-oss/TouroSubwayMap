package touro.subwaymap;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONReaderTest {

    @Test
    public void readJson() throws IOException {
        //given
        JSONReader jsonReader = new JSONReader();

        //when
        Stations stations = jsonReader.readJSON();

        //then
        assertEquals("Astor Pl", stations.features.get(0).properties.name);
        assertEquals(1, stations.features.get(0).properties.objectid);
        assertEquals(-73.99106999861966, stations.features.get(0).geometry.coordinates.get(0));
        assertEquals(40.73005400028978,stations.features.get(0).geometry.coordinates.get(1));
    }
}
