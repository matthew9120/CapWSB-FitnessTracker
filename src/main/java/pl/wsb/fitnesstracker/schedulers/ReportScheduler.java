package pl.wsb.fitnesstracker.schedulers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.email.JavaSpringEmailSender;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.internal.TrainingServiceImpl;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserServiceImpl;

@EnableScheduling
@Component
@Slf4j
public class ReportScheduler {

    private UserServiceImpl userService;
    private TrainingServiceImpl trainingService;
    private JavaSpringEmailSender emailSender;

    public ReportScheduler(UserServiceImpl userService, TrainingServiceImpl trainingService, JavaSpringEmailSender emailSender) {
        this.userService = userService;
        this.trainingService = trainingService;
        this.emailSender = emailSender;
    }

    @Scheduled(cron = "0 10 * * * 1")
    public void scheduleReportGenerate() {
        for (User user : userService.findAllUsers()) {
            StringBuilder output = new StringBuilder();
            output.append(user.getFirstName() + " " + user.getLastName() + ": (");

            for (Training training : trainingService.findTrainingsByUserForLastWeek(user)) {
                output.append(training.getId() + ": ");
                output.append("Start: " + training.getStartTime().toString());
                output.append("End: " + training.getEndTime().toString());
                output.append("ActivityType: " + training.getActivityType().getDisplayName());
                output.append("Distance: " + training.getDistance());
                output.append("AverageSpeed: " + training.getAverageSpeed());
                output.append(", ");
            }

            output.append(")");

            emailSender.send(user.getEmail(), "Raport", output.toString());
        }
    }
}
