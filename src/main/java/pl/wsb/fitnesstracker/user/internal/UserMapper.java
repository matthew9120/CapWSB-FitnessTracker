package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.BasicUserDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

/**
 * Mapper odpowiedzialny za konwersję obiektów użytkownika pomiędzy encją {@link User}
 * a obiektami DTO wykorzystywanymi w warstwie API.
 * <p>
 * Klasa zapewnia:
 * </p>
 * <ul>
 *     <li>mapowanie encji {@link User} do DTO: {@link BasicUserDto}, {@link UserDto}, {@link UserEmailDto}</li>
 *     <li>tworzenie encji {@link User} na podstawie {@link UserDto}</li>
 *     <li>częściową aktualizację encji {@link User} na podstawie pól niepustych w {@link UserDto}</li>
 * </ul>
 *
 * <p>
 * Mapper jest komponentem Spring ({@link Component}) i ma zasięg pakietowy (brak modyfikatora {@code public}),
 * co ogranicza jego użycie do warstwy wewnętrznej modułu użytkownika.
 * </p>
 */
@Component
class UserMapper {

    /**
     * Mapuje encję {@link User} na DTO zawierające podstawowe dane użytkownika.
     *
     * @param user encja użytkownika
     * @return DTO z podstawowymi danymi użytkownika
     */
    BasicUserDto toBasicDto(User user) {
        return new BasicUserDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    /**
     * Mapuje encję {@link User} na pełny DTO użytkownika.
     *
     * @param user encja użytkownika
     * @return DTO zawierające komplet podstawowych danych użytkownika
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Mapuje encję {@link User} na DTO zawierające identyfikator oraz adres e-mail użytkownika.
     *
     * @param user encja użytkownika
     * @return DTO zawierające identyfikator i e-mail użytkownika
     */
    UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }

    /**
     * Tworzy nową encję {@link User} na podstawie {@link UserDto}.
     * <p>
     * Metoda tworzy nowy obiekt użytkownika, nie ustawia identyfikatora (ID),
     * ponieważ jest on zazwyczaj generowany przez bazę danych.
     * </p>
     *
     * @param userDto DTO użytkownika
     * @return nowa encja użytkownika utworzona na podstawie danych z DTO
     */
    User fromDto(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email()
        );
    }

    /**
     * Aktualizuje istniejącą encję {@link User} na podstawie danych z {@link UserDto}.
     * <p>
     * Jest to aktualizacja częściowa: modyfikowane są wyłącznie te pola, które w DTO
     * mają wartość różną od {@code null}. Pozostałe pola encji pozostają bez zmian.
     * </p>
     *
     * @param user    encja użytkownika do aktualizacji
     * @param userDto DTO zawierające dane do aktualizacji
     * @return zaktualizowana encja użytkownika
     */
    User updateFromDto(User user, UserDto userDto) {
        if (userDto.firstName() != null) {
            user.setFirstName(userDto.firstName());
        }
        if (userDto.lastName() != null) {
            user.setLastName(userDto.lastName());
        }
        if (userDto.birthdate() != null) {
            user.setBirthdate(userDto.birthdate());
        }
        if (userDto.email() != null) {
            user.setEmail(userDto.email());
        }
        return user;
    }
}
