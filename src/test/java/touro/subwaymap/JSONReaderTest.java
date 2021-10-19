package touro.subwaymap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONReaderTest {

    @Test
    public void readJson(){
        //given
        JSONReader jsonReader = new JSONReader();

        //when
        Station station = jsonReader.readJSON();

        //then
        assertEquals("Astor Pl", station.features.get(0).properties.name);
        assertEquals(1, station.features.get(0).properties.objectid);
        assertEquals(-73.99106999861966, station.features.get(0).geometry.coordinates.get(0));
        assertEquals(40.73005400028978,station.features.get(0).geometry.coordinates.get(1));
    }
}
