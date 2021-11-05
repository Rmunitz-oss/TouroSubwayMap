package touro.subwaymap;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static touro.subwaymap.SubwayStations.*;

public class SubwayServiceTest {

    @Test
    public void getSubwayRoute(){
        //given
        //initialize, connect data, etc?
        SubwayService subwayService = new SubwayService();
        double [] startLocation = {70, 40}; //find coordinates to test
        double [] endLocation = {71,30}; //find coordinates to test
        ArrayList<Station> expectedShortestRoute = new ArrayList<>();
        expectedShortestRoute.add(mock(Station.class)); //add the correct stations?



        //when
        ArrayList<Station> shortestRoute = subwayService.getSubwayRoute(startLocation, endLocation);


        //then
        assertEquals(expectedShortestRoute,shortestRoute);
        
    }
}
