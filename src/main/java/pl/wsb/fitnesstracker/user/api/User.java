package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Encja reprezentująca użytkownika systemu FitnessTracker.
 * Przechowuje podstawowe dane identyfikacyjne i kontaktowe użytkownika, takie jak imię,
 * nazwisko, data urodzenia oraz unikalny adres e-mail. Encja posiada również powiązanie
 * z treningami użytkownika.
 *
 * Mapowanie JPA:
 * Encja jest mapowana na tabelę {@code users}. Pole {@code id} jest kluczem głównym
 * generowanym automatycznie. Relacja {@code OneToMany} do treningów oznacza, że jeden
 * użytkownik może posiadać wiele treningów.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    /**
     * Unikalny identyfikator użytkownika (klucz główny).
     * Wartość jest generowana automatycznie przez bazę danych.
     * Może być {@code null} dla obiektów, które nie zostały jeszcze zapisane w bazie.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    /**
     * Imię użytkownika.
     */
    @Column
    private String firstName;

    /**
     * Nazwisko użytkownika.
     */
    @Column
    private String lastName;

    /**
     * Data urodzenia użytkownika.
     * Pole jest wymagane (nie może być {@code null}).
     */
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    /**
     * Adres e-mail użytkownika.
     * Pole jest wymagane oraz musi być unikalne w bazie danych.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Zbiór treningów przypisanych do użytkownika.
     * Relacja jest mapowana przez pole {@code user} w encji {@link Training}.
     * Usunięcie użytkownika powoduje usunięcie jego treningów (kaskadowo),
     * a także usunięcie "osieroconych" rekordów treningów ({@code orphanRemoval=true}).
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private final Set<Training> trainings = new HashSet<>();

    /**
     * Tworzy nowego użytkownika na podstawie danych wejściowych.
     *
     * @param firstName  imię użytkownika
     * @param lastName   nazwisko użytkownika
     * @param birthdate  data urodzenia użytkownika (wymagana)
     * @param email      adres e-mail użytkownika (wymagany i unikalny)
     */
    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

    /**
     * Zwraca identyfikator użytkownika.
     *
     * @return identyfikator użytkownika lub {@code null}, jeśli encja nie została jeszcze zapisana w bazie
     */
    @Nullable
    public Long getId() {
        return id;
    }

    /**
     * Ustawia identyfikator użytkownika.
     * Zwykle nadawany automatycznie przez mechanizm persystencji (JPA).
     *
     * @param id identyfikator użytkownika lub {@code null}
     */
    public void setId(@Nullable Long id) {
        this.id = id;
    }

    /**
     * Zwraca imię użytkownika.
     *
     * @return imię użytkownika
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Ustawia imię użytkownika.
     *
     * @param firstName imię użytkownika
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Zwraca nazwisko użytkownika.
     *
     * @return nazwisko użytkownika
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Ustawia nazwisko użytkownika.
     *
     * @param lastName nazwisko użytkownika
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Zwraca datę urodzenia użytkownika.
     *
     * @return data urodzenia użytkownika
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Ustawia datę urodzenia użytkownika.
     *
     * @param birthdate data urodzenia użytkownika
     */
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Zwraca adres e-mail użytkownika.
     *
     * @return adres e-mail użytkownika
     */
    public String getEmail() {
        return email;
    }

    /**
     * Ustawia adres e-mail użytkownika.
     *
     * @param email adres e-mail użytkownika
     */
    public void setEmail(String email) {
        this.email = email;
    }
}

