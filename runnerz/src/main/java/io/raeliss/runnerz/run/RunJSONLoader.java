package io.raeliss.runnerz.run;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class RunJSONLoader implements CommandLineRunner {

    private final Logger log = (Logger) LoggerFactory.getLogger(RunJSONLoader.class);
    private final JdbcClientRunRepository runRepository;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public RunJSONLoader(JdbcClientRunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (runRepository.count() == 0) {
            log.info("Loading JSON data into the database...");
            try (InputStream inputStream = getClass().getResourceAsStream("/data/runs.json")) {
                if (inputStream != null) {
                    Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                    runRepository.saveAll(allRuns.runs());
                    log.info("JSON data loaded successfully.");
                } else {
                    log.error("Could not find JSON file.");
                }
            } catch (IOException e) {
                log.error("Error loading JSON data: " + e.getMessage(), e);

            }
        } else {
            log.info("Database already contains data, skipping JSON load.");
        }
    }
}
