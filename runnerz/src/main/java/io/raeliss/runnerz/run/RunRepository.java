package io.raeliss.runnerz.run;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    void create(Run run) {
        runs.add(run);
    }

    boolean update(Run run, Integer findById) {
        Optional<Run> runOptional = findById(findById);
        if (runOptional.isPresent()) {
            runs.remove(runOptional.get());
            runs.add(run);
            return true;
        } else {
            return false;
        }
    }

    void delete(Integer id) {
        Optional<Run> runOptional = findById(id);
        if (runOptional.isPresent()) {
            runs.remove(runOptional.get());
        }
    }

    public List<Run> findAll() {
        return runs;
    }

    public Optional<Run> findById(int id) {
        return Optional.ofNullable(runs.stream()
                .filter(run -> run.id() == id)
                .findFirst()
                .orElse(null));
    }

}
