package pl.wsb.fitnesstracker.training.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query(value = "SELECT t FROM Training t WHERE t.user = %:user")
    List<Training> findAllTrainingsByUser(@Param("user") Optional<User> user);
}
