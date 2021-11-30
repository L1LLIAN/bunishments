package dev.lillian.punishments.api;

import dev.lillian.punishments.api.punishment.Punishment;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

public interface IPunishmentsAPI {
    @NotNull
    Collection<Punishment> findPunishmentsByPunished(@NotNull UUID uuid);

    @NotNull
    Collection<Punishment> findPunishmentsByPunisher(@NotNull UUID uuid);
}
