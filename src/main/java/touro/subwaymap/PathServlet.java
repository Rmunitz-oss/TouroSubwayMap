package touro.subwaymap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class PathServlet extends HttpServlet {

    private final SubwayService subwayService;
    private final Gson gson;

    public PathServlet() throws IOException {
        subwayService = new SubwayService();
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response
        ) throws IOException {
        PrintWriter out = response.getWriter();
        double [] startLocation = {
                Double.parseDouble(request.getParameter("lon1")),
                Double.parseDouble(request.getParameter("lat1"))
        };
        double [] endLocation = {
                Double.parseDouble(request.getParameter("lon2")),
                Double.parseDouble(request.getParameter("lat2"))
        };
        ArrayList<SubwayStations.Station> shortestRoute = subwayService.getSubwayRoute(startLocation,endLocation);
        String json = gson.toJson(shortestRoute);
        out.println(json);
    }

}
