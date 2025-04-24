package io.raeliss.runnerz.run;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    public List<Run> getRuns() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    public Run getRunById(@PathVariable int id) {
        Optional<Run> runOptional = runRepository.findById(id);
        if (runOptional.isEmpty()) {
            throw new RunNotFoundException();
        } else {
            return runOptional.get();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {
        runRepository.create(run);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    void update(@RequestBody Run run, @PathVariable Integer id) {
        if (!runRepository.update(run, id)) {
            throw new RunNotFoundException();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }

}
