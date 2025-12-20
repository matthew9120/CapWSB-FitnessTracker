package pl.wsb.fitnesstracker.training.internal;

import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.exception.api.NotFoundException;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * TrainingController is responsible for handling HTTP requests related to training operations.
 * It provides endpoints for retrieving trainings.
 */
@RestController
@RequestMapping("/v1/trainings")
class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;
    private final UserRepository userRepository;

    public TrainingController(
            TrainingServiceImpl trainingService,
            TrainingMapper trainingMapper,
            UserRepository userRepository
        ) {
        this.trainingService = trainingService;
        this.trainingMapper = trainingMapper;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return this.trainingService.findAllTrainings()
                .stream()
                .map(this.trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/by-user/{userId}")
    public List<TrainingDto> getAllTrainingsByUser(@PathVariable Long userId) {
        Optional<User> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new NotFoundException("Not found");
        }

        return this.trainingService.findAllTrainingsByUser(user)
                .stream()
                .map(this.trainingMapper::toDto)
                .toList();
    }
}

