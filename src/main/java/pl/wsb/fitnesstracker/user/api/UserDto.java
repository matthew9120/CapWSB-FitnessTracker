package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * DTO uzytkownika zawierające komplet podstawowych danych.
 * Obiekt sluzy do wymiany danych w warstwie API (np. odpowiedzi endpointow),
 * obejmujac identyfikator, dane osobowe, date urodzenia oraz adres e-mail.
 *
 * Pole {@code birthdate} jest serializowane/deserializowane w formacie {@code yyyy-MM-dd}
 * zgodnie z adnotacja {@link JsonFormat}.
 *
 * @param id        identyfikator uzytkownika; moze byc {@code null}, jesli encja nie zostala jeszcze zapisana w bazie
 * @param firstName imie uzytkownika
 * @param lastName  nazwisko uzytkownika
 * @param birthdate data urodzenia uzytkownika w formacie {@code yyyy-MM-dd}
 * @param email     adres e-mail uzytkownika
 */
public record UserDto(@Nullable Long id, String firstName, String lastName,
                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                      String email) {

}
