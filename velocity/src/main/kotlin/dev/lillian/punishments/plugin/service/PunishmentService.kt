package dev.lillian.punishments.plugin.service

import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import dev.lillian.punishments.api.punishment.Punishment
import dev.lillian.punishments.api.punishment.PunishmentType
import dev.lillian.punishments.api.punishment.PunishmentType.*
import dev.lillian.punishments.plugin.repository.IPunishmentRepository
import net.kyori.adventure.text.Component
import java.util.*

class PunishmentService(private val punishmentRepository: IPunishmentRepository, private val proxyServer: ProxyServer) {
    // TODO: Support console bans
    // Should have default UUID(0, 0) with name Console* to indicate that it isn't a player punisher.
    fun punish(target: Player, punisher: Player, type: PunishmentType, duration: Long) {
        val punishment = Punishment(
            UUID.randomUUID(),
            type,
            System.currentTimeMillis(),
            duration,
            target.uniqueId,
            punisher.uniqueId,
            null,
            Punishment.PERMANENT_DURATION
        )

        punishmentRepository.save(punishment)
        update(punishment)
    }

    private fun update(punishment: Punishment) {
        val punishedPlayer = proxyServer.getPlayer(punishment.punishedId).orElse(null) ?: return

        when (punishment.type) {
            BLACKLIST -> TODO()
            BAN -> TODO()
            KICK -> punishedPlayer.disconnect(Component.text("You have been kicked!"))
            MUTE -> TODO()
            WARNING -> TODO()
        }
    }
}