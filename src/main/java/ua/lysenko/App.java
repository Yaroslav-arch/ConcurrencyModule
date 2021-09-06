package ua.lysenko;

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

    public void run(Bet bet) throws InterruptedException, ExecutionException {
        List<Race> tasks = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            tasks.add(new Race(i));
        }
        LocalDate localDate = LocalDate.now();
        List<Future<Horse>> futures = executor.invokeAll(tasks);
        List<Horse> results = new ArrayList<>();
        for (Future<Horse> future : futures) {
            Horse horse = future.get();
            RaceModel model = horseToModel(horse, localDate, bet);
            results.add(horse);

        }
        executor.shutdown();
        System.out.println(results);
    }

    private static RaceModel horseToModel(Horse horse, LocalDate localDate, Bet bet) {
        RaceModel model = new RaceModel();
        model.setRaceId(Counter.getCounter());
        model.setHorseId(horse.getNumber());
        model.setResult(horse.getResultTime());
//        model.setPosition();
        model.setDate(localDate);
        if (model.getHorseId() == bet.getChosen()) {
            model.setBet(true);
        } else {
            model.setBet(false);
        }
        return model;
    }
}
