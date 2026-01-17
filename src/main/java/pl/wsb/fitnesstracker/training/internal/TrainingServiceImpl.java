package pl.wsb.fitnesstracker.training.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    TrainingServiceImpl(final TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    public List<Training> findAllTrainingsByUser(Optional<User> user) {
        return trainingRepository.findAllTrainingsByUser(user);
    }

    public List<Training> findTrainingsByUserForLastWeek(User user) {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusWeeks(1);
        return trainingRepository.findTrainingsByUserForLastWeek(user, sevenDaysAgo);
    }


}
