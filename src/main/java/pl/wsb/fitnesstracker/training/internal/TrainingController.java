package pl.wsb.fitnesstracker.training.internal;

import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

import java.util.List;

/**
 * TrainingController is responsible for handling HTTP requests related to training operations.
 * It provides endpoints for retrieving trainings.
 */
@RestController
@RequestMapping("/v1/trainings")
class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    public TrainingController(TrainingServiceImpl trainingService, TrainingMapper trainingMapper) {
        this.trainingService = trainingService;
        this.trainingMapper = trainingMapper;
    }

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return this.trainingService.findAllTrainings()
                .stream()
                .map(this.trainingMapper::toDto)
                .toList();
    }
}

