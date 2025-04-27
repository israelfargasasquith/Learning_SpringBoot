package io.raeliss.runnerz.run;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class JdbcClientRunRepository {

    private List<Run> runs;
    private final JdbcClient jdbcClient;

    public JdbcClientRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM RUN")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(int id) {
        return jdbcClient.sql("SELECT * FROM RUN WHERE ID = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        jdbcClient.sql(
                "INSERT INTO RUN (ID, TITLE, STARTED_ON, COMPLETED_ON, KM, LOCATION) VALUES (:id, :title, :startedOn, :completedOn, :km, :location)")
                .param("id", run.id())
                .param("title", run.title())
                .param("startedOn", run.startedOn())
                .param("completedOn", run.completedOn())
                .param("km", run.km())
                .param("location", run.location().toString())
                .update();
    }

    public boolean update(Run run, Integer findById) {
        return jdbcClient.sql(
                "UPDATE RUN SET TITLE = :title, STARTED_ON = :startedOn, COMPLETED_ON = :completedOn, KM = :km, LOCATION = :location WHERE ID = :id")
                .param("title", run.title())
                .param("startedOn", run.startedOn())
                .param("completedOn", run.completedOn())
                .param("km", run.km())
                .param("location", run.location().toString())
                .param("id", findById)
                .update() > 0;
    }

    public void delete(Integer id) {
        jdbcClient.sql("DELETE FROM RUN WHERE ID = :id")
                .param("id", id)
                .update();
    }

    public int count() {
        return jdbcClient.sql("SELECT COUNT(*) FROM RUN")
                .query(Integer.class)
                .single();

    }

    public void saveAll(List<Run> runs) {
        for (Run run : runs) {
            create(run);
        }
    }

    // this is in memory data
    // @PostConstruct
    // private void init() {
    // runs = new ArrayList<>();
    // runs.add(new Run(1, "Morning Run", null, null, 5.0, Location.INDOOR));
    // runs.add(new Run(2, "Evening Run", null, null, 10.0, Location.OUTDOOR));
    // }

    // void create(Run run) {
    // runs.add(run);
    // }

    // boolean update(Run run, Integer findById) {
    // Optional<Run> runOptional = findById(findById);
    // if (runOptional.isPresent()) {
    // runs.remove(runOptional.get());
    // runs.add(run);
    // return true;
    // } else {
    // return false;
    // }
    // }

    // void delete(Integer id) {
    // Optional<Run> runOptional = findById(id);
    // if (runOptional.isPresent()) {
    // runs.remove(runOptional.get());
    // }
    // }

    // public List<Run> findAll() {
    // return runs;
    // }

    // public Optional<Run> findById(int id) {
    // return Optional.ofNullable(runs.stream()
    // .filter(run -> run.id() == id)
    // .findFirst()
    // .orElse(null));
    // }

}
