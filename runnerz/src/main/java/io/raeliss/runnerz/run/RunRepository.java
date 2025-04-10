package io.raeliss.runnerz.run;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {

    private List<Run> runs;

    @PostConstruct
    private void init() {
        runs = new ArrayList<>();
        runs.add(new Run(1, "Morning Run", null, null, 5.0, Location.INDOOR));
        runs.add(new Run(2, "Evening Run", null, null, 10.0, Location.OUTDOOR));
    }

    public List<Run> findAll() {
        return runs;
    }

    public Run findById(int id) {
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst()
                .orElse(null);
    }

}
