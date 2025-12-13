package pl.wsb.fitnesstracker.user.api;

import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);


    /**
     * @param id to be removed
     */
    void deleteUser(Long id);

    /**
     * @param age to filter
     * @return from findAll()
     */
    List<User> findUsersOlderThan(int age);


    /**
     * @param user to be saved
     * @return same User
     */
    User saveUser(User user);

}
