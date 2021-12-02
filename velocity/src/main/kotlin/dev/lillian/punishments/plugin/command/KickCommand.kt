package dev.lillian.punishments.plugin.command

import com.velocitypowered.api.proxy.Player
import dev.lillian.punishments.api.punishment.Punishment
import dev.lillian.punishments.api.punishment.PunishmentType
import dev.lillian.punishments.plugin.service.PunishmentService
import net.kyori.adventure.text.Component
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Default
import revxrsal.commands.annotation.Dependency

@Command("kick")
class KickCommand {
    @Dependency
    private lateinit var punishmentService: PunishmentService

    @Default
    fun execute(punisher: Player, target: Player) {
        punishmentService.punish(target, punisher, PunishmentType.KICK, Punishment.PERMANENT_DURATION)

        punisher.sendMessage(Component.text("You kicked ${target.username}!"))
    }
}