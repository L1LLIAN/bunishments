package dev.lillian.punishments.plugin.command

import com.velocitypowered.api.command.CommandSource
import com.velocitypowered.api.proxy.Player
import dev.lillian.punishments.api.punishment.Punishment
import dev.lillian.punishments.api.punishment.PunishmentType
import dev.lillian.punishments.plugin.service.PunishmentService
import net.kyori.adventure.text.Component
import revxrsal.commands.annotation.*

@Command("kick")
class KickCommand {
    @Dependency
    private lateinit var punishmentService: PunishmentService

    @Default
    @Usage("<target>")
    fun execute(punisher: CommandSource, @Named("target") target: Player) {
        punishmentService.punish(target, punisher, PunishmentType.KICK, Punishment.PERMANENT_DURATION)

        punisher.sendMessage(Component.text("You kicked ${target.username}!"))
    }
}