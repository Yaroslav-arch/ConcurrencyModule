package ua.lysenko.servlets;

import com.google.gson.Gson;
import ua.lysenko.dao.RaceModelDao;
import ua.lysenko.entity.RaceModel;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;


public class SecondServlet extends HttpServlet {

    Gson gson = new Gson();
    RaceModel raceModel = new RaceModel();
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
        resp.getWriter().write(gson.toJson(raceModel));
        raceModelDao.getInfoAboutRace(raceId);
    }

    @Override
    public void destroy() {
        log("Hasta la vista!");
    }
}
