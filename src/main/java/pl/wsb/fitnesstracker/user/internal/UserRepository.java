package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return findAll() list
     */
    default List<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .toList();
    }

    /**
     * @param age
     * @return findAll() list
     */
    default List<User> findUsersOlderThan(int age) {
        LocalDate date = LocalDate.now();
        LocalDate birthday = date.withYear(date.getYear() - age);

        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(birthday))
                .toList();
    }

}
