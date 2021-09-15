package ua.lysenko.servlets;

import ua.lysenko.dao.RaceModelDao;
import ua.lysenko.entity.RaceModel;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class SecondServlet extends HttpServlet {

    RaceModelDao raceModelDao = new RaceModelDao();


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log("It is alive!");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long raceId = Long.parseLong(req.getPathInfo().substring(1));

        List<RaceModel> infoAboutRace = raceModelDao.getInfoAboutRace(raceId);
        resp.getWriter().write("The race was held on " + infoAboutRace.get(0).getDate() + "\n");
        resp.getWriter().write("We had " + infoAboutRace.size() + " competitors \n");

        for (RaceModel model : infoAboutRace) {
            if (model.isBet()) {
                resp.getWriter().write("Position: " + model.getPosition() + "; Horse number: " + model.getHorseId() + " - Your bet\n");
            } else {
                resp.getWriter().write("Position: " + model.getPosition() + "; Horse number: " + model.getHorseId() + "\n");
            }
        }
    }

    @Override
    public void destroy() {
        log("Hasta la vista!");
    }
}
