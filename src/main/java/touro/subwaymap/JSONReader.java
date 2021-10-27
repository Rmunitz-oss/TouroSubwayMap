package touro.subwaymap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class JSONReader {
    private final Gson gson = new Gson();
    private Reader reader;

    public SubwayStations readSubwayStationJSON() throws IOException {
        reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayStations.json"));
        SubwayStations subwayStations = gson.fromJson(reader, SubwayStations.class);
        reader.close();
        return subwayStations;
    }

    public Map<String,ArrayList<Integer>> readSubwayLinesJSON() throws IOException {
        reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayLines.json"));
        Type type = new TypeToken<HashMap<String,ArrayList<Integer>>>(){}.getType();
        Map<String,ArrayList<Integer>> subwayLines = gson.fromJson(reader, type);
        reader.close();
        return subwayLines;
    }
}
