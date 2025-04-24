package io.raeliss.runnerz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record Run(
                Integer id,
                @NotEmpty
                @NotNull
                String title,
                LocalDateTime startedOn,
                LocalDateTime completedOn,
                @Positive
                @NotNull
                Double km,
                Location location) {

}
