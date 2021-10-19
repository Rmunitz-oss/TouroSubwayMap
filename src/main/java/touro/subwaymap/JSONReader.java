package touro.subwaymap;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONReader {
    public Station station;

    public Station readJSON(){
        try{
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayStations.json"));
            station = gson.fromJson(reader, Station.class);
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return station;
    }
}
