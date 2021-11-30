package dev.lillian.punishments.plugin

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import dev.lillian.punishments.api.IPunishmentsAPI
import dev.lillian.punishments.api.punishment.Punishment
import dev.lillian.punishments.plugin.config.PluginConfiguration
import org.slf4j.Logger
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import javax.inject.Inject

@Plugin(
    id = "bunishments",
    name = "Bunishments",
    version = "@@VERSION@@",
    description = "Simple punishments plugin for simple people.",
    url = "https://github.com/L1LLIAN/bunishments/",
    authors = ["Lillian Armes - lilyarmes@protonmail.com"]
)
class VelocityPlugin @Inject constructor(
    @DataDirectory private val dataDir: Path,
    private val logger: Logger,
    private val proxyServer: ProxyServer
) : IPunishmentsAPI {
    val objectMapper = jacksonObjectMapper()

    val config = loadConfig()

    init {
        logger.info(config.toString())
    }

    private fun loadConfig(): PluginConfiguration {
        val configFile = File(dataDir.toFile(), "config.json")

        if (!configFile.exists()) {
            logger.info("No configuration file found, saving and using default!")

            val defaultConfig = PluginConfiguration("mongodb://127.0.0.1:27107/?authSource=admin")

            Files.createDirectories(dataDir)
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(configFile, defaultConfig)

            return defaultConfig
        }

        return objectMapper.readValue(configFile, PluginConfiguration::class.java)
    }

    override fun findPunishmentsByPunished(uuid: UUID): MutableCollection<Punishment> {
        TODO("Not yet implemented")
    }

    override fun findPunishmentsByPunisher(uuid: UUID): MutableCollection<Punishment> {
        TODO("Not yet implemented")
    }
}