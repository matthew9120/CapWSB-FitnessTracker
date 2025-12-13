package pl.wsb.fitnesstracker.user.internal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.BasicUserDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<BasicUserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toBasicDto)
                .toList();
    }

    @GetMapping("/{id}")
    public Object[] getUser(@PathVariable Long id) {
        return this.userService.getUser(id)
                .stream()
                .map(this.userMapper::toDto)
                .toArray();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object[] createUser(@RequestBody UserDto userDto) {
        User createdUser = this.userService.createUser(this.userMapper.fromDto(userDto));

        return this.userService.getUser(createdUser.getId())
                .stream()
                .map(this.userMapper::toDto)
                .toArray();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
    }

    @GetMapping("/by-email/{email}")
    public List<UserEmailDto> getUserByEmail(@PathVariable String email) {
        return this.userService.getUsersByEmail(email)
                .stream()
                .map(this.userMapper::toEmailDto)
                .toList();
    }

    @GetMapping("/by-age/{age}")
    public List<UserDto> getUsersByAge(@PathVariable int age) {
        return this.userService.findUsersOlderThan(age)
                .stream()
                .map(this.userMapper::toDto)
                .toList();
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = this.userService.getUser(id).get();
        this.userMapper.updateFromDto(user, userDto);
        this.userService.saveUser(user);
    }
}

