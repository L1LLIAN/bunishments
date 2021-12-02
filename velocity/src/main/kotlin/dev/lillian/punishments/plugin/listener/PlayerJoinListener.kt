package dev.lillian.punishments.plugin.listener

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent
import dev.lillian.punishments.api.punishment.PunishmentType
import dev.lillian.punishments.plugin.repository.IPunishmentRepository
import net.kyori.adventure.text.Component

class PlayerJoinListener(private val punishmentRepository: IPunishmentRepository) {
    @Subscribe
    fun onPlayerJoin(event: PlayerChooseInitialServerEvent) {
        val activeBans = punishmentRepository.findByPunishedId(event.player.uniqueId)
            .filter { it.type == PunishmentType.BAN }
            .filter { System.currentTimeMillis() > it.timestamp + it.duration }

        // not banned
        if (activeBans.isEmpty()) {
            return
        }

        event.player.disconnect(Component.text("You're banned!"))
    }
}