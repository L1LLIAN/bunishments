package dev.lillian.punishments.plugin

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import dev.lillian.punishments.api.IPunishmentsAPI
import dev.lillian.punishments.api.punishment.Punishment
import dev.lillian.punishments.plugin.command.KickCommand
import dev.lillian.punishments.plugin.config.PluginConfiguration
import dev.lillian.punishments.plugin.model.PunishmentDTO
import dev.lillian.punishments.plugin.repository.MongoPunishmentRepository
import dev.lillian.punishments.plugin.service.PunishmentService
import org.bson.UuidRepresentation
import org.mongojack.JacksonMongoCollection
import org.slf4j.Logger
import revxrsal.commands.velocity.core.VelocityHandler
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

    val mongoClient = MongoClients.create(
        MongoClientSettings.builder()
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .applyConnectionString(ConnectionString(config.mongoUri))
            .build()
    )

    init {
        val collection = JacksonMongoCollection.builder()
            .withObjectMapper(objectMapper)
            .build(
                mongoClient,
                config.mongoDatabase,
                "punishments",
                PunishmentDTO::class.java,
                UuidRepresentation.STANDARD
            )

        val repository = MongoPunishmentRepository(collection)
        val service = PunishmentService(repository, proxyServer)

        val commandHandler = VelocityHandler(this, proxyServer)

        commandHandler.registerDependency(PunishmentService::class.java, service)
        commandHandler.register(KickCommand())
    }

    @Subscribe
    @Suppress("UNUSED_PARAMETER")
    fun onShutdown(event: ProxyShutdownEvent) {
        mongoClient.close()
    }

    private fun loadConfig(): PluginConfiguration {
        val configFile = File(dataDir.toFile(), "config.json")

        if (!configFile.exists()) {
            logger.info("No configuration file found, saving and using default!")

            val defaultConfig = PluginConfiguration("mongodb://127.0.0.1:27017/?authSource=admin", "bunishments-dev")

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