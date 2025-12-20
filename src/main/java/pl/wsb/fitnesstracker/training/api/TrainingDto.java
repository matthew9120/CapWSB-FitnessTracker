package pl.wsb.fitnesstracker.training.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

public record TrainingDto(@Nullable Long id, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate startTime,
                          @JsonFormat(pattern = "yyyy-MM-dd") LocalDate endTime,
                          double distance, double averageSpeed) {

}
