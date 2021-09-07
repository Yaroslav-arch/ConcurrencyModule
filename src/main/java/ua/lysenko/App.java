package ua.lysenko;

import ua.lysenko.dao.RaceModelDao;
import ua.lysenko.entity.Bet;
import ua.lysenko.entity.Counter;
import ua.lysenko.entity.Horse;
import ua.lysenko.entity.RaceModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
    RaceModelDao dao = new RaceModelDao();

    public void run(Bet bet) throws InterruptedException, ExecutionException {
        List<Race> tasks = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            tasks.add(new Race(i));
        }
        LocalDate localDate = LocalDate.now();
        List<Future<Horse>> futures = executor.invokeAll(tasks);
        List<Horse> horses = new ArrayList<>();
        for (Future<Horse> future : futures) {
           Horse horse = future.get();
            horses.add(horse);
        }
        horses.sort((h1, h2) -> (int) (h2.getResultTime() - h1.getResultTime()));
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).setPosition(i + 1);
        }
        for (Horse horse : horses) {
            dao.saveRaceModel(horseToModel(horse, localDate, bet));
        }

        executor.shutdown();
//        System.out.println(horses);
    }

    private static RaceModel horseToModel(Horse horse, LocalDate localDate, Bet bet) {
        RaceModel model = new RaceModel();
        model.setRaceId(Counter.getCounter());
        model.setHorseId(horse.getNumber());
        model.setResult(horse.getResultTime());
        model.setPosition(horse.getPosition());
        model.setDate(localDate);
        model.setBet(model.getHorseId() == bet.getChosen());

        return model;
    }
}
