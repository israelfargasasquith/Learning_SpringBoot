package io.raeliss.runnerz.run;

import java.time.LocalDateTime;

public record Run(Integer id, String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Double km,
        Location location) {
}
