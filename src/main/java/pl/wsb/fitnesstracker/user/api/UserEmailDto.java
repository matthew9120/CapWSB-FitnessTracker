package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * DTO użytkownika zawierające wyłącznie identyfikator oraz adres e-mail.
 * Służy do przekazywania minimalnych informacji o użytkowniku w sytuacjach,
 * w których potrzebny jest tylko e-mail (np. do weryfikacji/wyświetlenia danych kontaktowych),
 * bez pozostałych danych osobowych.
 *
 * @param id    identyfikator użytkownika; może być {@code null}, jeśli encja nie została jeszcze zapisana w bazie
 * @param email adres e-mail użytkownika
 */
public record UserEmailDto(@Nullable Long id, String email) {}
