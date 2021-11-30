package dev.lillian.punishments.plugin.model

import dev.lillian.punishments.api.punishment.PunishmentType
import org.mongojack.ObjectId
import java.util.*

data class PunishmentDTO(
    @ObjectId val _id: String? = null,
    val id: UUID,
    val type: PunishmentType,
    val timestamp: Long,
    val duration: Long,
    val punishedID: UUID,
    val punisherId: UUID,
    val removerId: UUID?,
    val removalTimestamp: Long
)