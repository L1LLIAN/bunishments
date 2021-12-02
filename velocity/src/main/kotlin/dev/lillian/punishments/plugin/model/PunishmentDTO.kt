package dev.lillian.punishments.plugin.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import dev.lillian.punishments.api.punishment.PunishmentType
import java.util.*

@JsonIgnoreProperties("_id")
data class PunishmentDTO(
    val id: UUID,
    val type: PunishmentType,
    val timestamp: Long,
    val duration: Long,
    val punishedId: UUID,
    val punisherId: UUID,
    val removerId: UUID?,
    val removalTimestamp: Long
)