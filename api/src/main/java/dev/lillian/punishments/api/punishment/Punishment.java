package dev.lillian.punishments.api.punishment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public final class Punishment {
    public static final long PERMANENT_DURATION = -1;

    /**
     * The ID used for unique storage of punishments
     */
    @NotNull
    private final UUID id;

    /**
     * The type of punishment
     */
    @NotNull
    private final PunishmentType type;

    /**
     * The time in epoch-ms this punishment was originally applied at
     */
    private final long timestamp;

    /**
     * The time in epoch-ms this punishment should last for, or {@link Punishment#PERMANENT_DURATION}
     */
    private final long duration;

    /**
     * The UUID of the player who this punishment was given to
     */
    @NotNull
    private final UUID punishedId;

    /**
     * The UUID of the player who gave out this punishment
     */
    @NotNull
    private final UUID punisherId;

    /**
     * The UUID of the player who removed this punishment, or null if it hasn't been removed
     */
    @Nullable
    private UUID removerId;

    /**
     * The time in epoch-ms this punishment was removed at, or {@link Punishment#PERMANENT_DURATION} if the punishment has not been removed.
     */
    private long removalTimestamp;
}
