package quest.laxla.smaug

import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Smaug : ModInitializer {
    private const val Namespace: String = "smaug"
    val LOGGER: Logger = LoggerFactory.getLogger("Smaug")
    val NoPassengerFallDamage = TagKey.of(RegistryKeys.BLOCK, Smaug(path = "no_passenger_fall_damage"))

    override fun onInitialize(mod: ModContainer) {
        LOGGER.info("Hello Quilt world from {}!", mod.metadata()?.name())
    }

    operator fun invoke(path: String) = Identifier(Namespace, path)
}
