package dev.lillian.punishments.plugin.repository

import dev.lillian.punishments.api.punishment.Punishment
import java.util.*

interface IPunishmentRepository {
    fun save(punishment: Punishment)

    fun findById(uuid: UUID): Punishment?

    fun findByPunishedId(uuid: UUID): Iterable<Punishment>

    fun findByPunisherId(uuid: UUID): Iterable<Punishment>
}