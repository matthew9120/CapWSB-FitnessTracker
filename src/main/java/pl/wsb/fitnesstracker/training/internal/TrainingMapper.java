package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

import java.time.LocalDate;
import java.time.ZoneId;

@Component
class TrainingMapper {

    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getId(),
                LocalDate.ofInstant(training.getStartTime().toInstant(), ZoneId.systemDefault()),
                LocalDate.ofInstant(training.getEndTime().toInstant(), ZoneId.systemDefault()),
                training.getDistance(),
                training.getAverageSpeed());
    }
}
