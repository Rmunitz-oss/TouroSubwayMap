package touro.subwaymap;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class JSONReader {
    private final Gson gson = new Gson();
    private Reader reader;

    public SubwayStations readSubwayStationJSON() throws IOException {
        reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayStations.json"));
        SubwayStations subwayStations = gson.fromJson(reader, SubwayStations.class);
        reader.close();
        return subwayStations;
    }

    //reading directly into a map?
    public Map<String,ArrayList<String>> readSubwayLinesJSON() throws IOException {
        reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayLines.json"));
        Map<String,ArrayList<String>> subwayLines = gson.fromJson(reader, Map.class);
        reader.close();
        return subwayLines;
    }
}
