package dev.lillian.punishments.plugin.service

import com.velocitypowered.api.command.CommandSource
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import dev.lillian.punishments.api.punishment.Punishment
import dev.lillian.punishments.api.punishment.PunishmentType
import dev.lillian.punishments.api.punishment.PunishmentType.*
import dev.lillian.punishments.plugin.repository.IPunishmentRepository
import net.kyori.adventure.text.Component
import java.util.*

class PunishmentService(private val punishmentRepository: IPunishmentRepository, private val proxyServer: ProxyServer) {
    fun punish(target: Player, punisher: CommandSource, type: PunishmentType, duration: Long) {
        val punishment = Punishment(
            UUID.randomUUID(),
            type,
            System.currentTimeMillis(),
            duration,
            target.uniqueId,
            if (punisher is Player) punisher.uniqueId else UUID(0, 0),
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
            BAN -> punishedPlayer.disconnect(Component.text("You have been banned!"))
            KICK -> punishedPlayer.disconnect(Component.text("You have been kicked!"))
            MUTE -> TODO()
            WARNING -> TODO()
        }
    }
}