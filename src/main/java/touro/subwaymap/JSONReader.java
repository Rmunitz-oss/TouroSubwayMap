package touro.subwaymap;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONReader {
    public Stations stations;

    public Stations readJSON() throws IOException {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayStations.json"));
            stations = gson.fromJson(reader, Stations.class);
            reader.close();
        return stations;
    }
}
