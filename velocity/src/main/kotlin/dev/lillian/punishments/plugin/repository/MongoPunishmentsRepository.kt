package dev.lillian.punishments.plugin.repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import dev.lillian.punishments.api.punishment.Punishment
import dev.lillian.punishments.plugin.model.PunishmentDTO
import java.util.*

class MongoPunishmentsRepository(private val mongoCollection: MongoCollection<PunishmentDTO>) :
    IPunishmentsRepository {

    private fun Punishment.toDTO(): PunishmentDTO {
        return PunishmentDTO(null, id, type, timestamp, duration, punishedId, punisherId, removerId, removalTimestamp)
    }

    private fun PunishmentDTO.toPunishment(): Punishment {
        return Punishment(id, type, timestamp, duration, punishedID, punisherId, removerId, removalTimestamp)
    }

    override fun save(punishment: Punishment) {
        mongoCollection.insertOne(punishment.toDTO())
    }

    override fun findById(uuid: UUID): Punishment? {
        return mongoCollection.find(Filters.eq("id", uuid)).first()?.toPunishment()
    }

    override fun findByPunishedId(uuid: UUID): Collection<Punishment> {
        TODO("Not yet implemented")
    }

    override fun findByPunisherId(uuid: UUID): Collection<Punishment> {
        TODO("Not yet implemented")
    }
}