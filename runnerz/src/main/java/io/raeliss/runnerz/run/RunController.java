package io.raeliss.runnerz.run;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return runRepository.findAll().get(id);// runRepository.findById(id);
    }

}
