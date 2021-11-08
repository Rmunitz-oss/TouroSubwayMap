package touro.subwaymap;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static touro.subwaymap.SubwayStations.*;

public class SubwayServiceTest {

    @Test
    public void getSubwayRoute() throws IOException {
        //given
        SubwayService subwayService = new SubwayService();
        SubwayStations subwayStations = subwayService.getSubwayStations();
        double [] startLocation = {-73.97754993539385, 40.68442016526762}; //coordinates of Atlantic Av - Barclay Center
        double [] endLocation = { -73.98192900232715, 40.76824700063689}; //coordinates of 59th St - Columbus Circle
        ArrayList<Station> expectedShortestRoute = new ArrayList<>();
        expectedShortestRoute.add(subwayStations.stations.get(115)); //Atlantic Av - Barclay's Center (start)
        expectedShortestRoute.add(subwayStations.stations.get(127)); //Nevins St
        expectedShortestRoute.add(subwayStations.stations.get(404)); //Hoyt St
        expectedShortestRoute.add(subwayStations.stations.get(405)); //Borough Hall
        expectedShortestRoute.add(subwayStations.stations.get(445)); //Clark St
        expectedShortestRoute.add(subwayStations.stations.get(401)); //Fulton St
        expectedShortestRoute.add(subwayStations.stations.get(402)); //Park Pl
        expectedShortestRoute.add(subwayStations.stations.get(403)); //Chambers St
        expectedShortestRoute.add(subwayStations.stations.get(438)); //14th St
        expectedShortestRoute.add(subwayStations.stations.get(357)); //34th St - Penn Station
        expectedShortestRoute.add(subwayStations.stations.get(358)); //Times Sq - 42nd St
        expectedShortestRoute.add(subwayStations.stations.get(2));   //50th St
        expectedShortestRoute.add(subwayStations.stations.get(93));  //59th St - Columbus Circle (end)

        //when
        ArrayList<Station> shortestRoute = subwayService.getSubwayRoute(startLocation,endLocation);

        //then
        assertEquals(expectedShortestRoute,shortestRoute);
        
    }
}
