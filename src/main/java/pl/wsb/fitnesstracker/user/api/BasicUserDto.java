package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * Podstawowy DTO użytkownika.
 * Służy do przekazywania skróconych informacji o użytkowniku (np. w listach lub podglądach),
 * bez pełnych danych szczegółowych.
 *
 * @param id        identyfikator użytkownika; może być {@code null}, jeśli obiekt nie został jeszcze zapisany w bazie
 * @param firstName imię użytkownika
 * @param lastName  nazwisko użytkownika
 */
public record BasicUserDto(@Nullable Long id, String firstName, String lastName) {}
