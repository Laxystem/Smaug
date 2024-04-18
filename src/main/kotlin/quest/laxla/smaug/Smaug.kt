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
    val Logger: Logger = LoggerFactory.getLogger("Smaug")
    val MaxSpeedMultiplier = 2.0
    val AirborneMinecartSpeedMultiplier = 0.99
    @JvmField
    val NoPassengerFallDamage = TagKey.of(RegistryKeys.BLOCK, Smaug(path = "no_passenger_fall_damage"))
    @JvmField
    val IncreasesMinecartMaxSpeed = TagKey.of(RegistryKeys.BLOCK, Smaug(path = "increases_minecart_max_speed"))

    override fun onInitialize(mod: ModContainer) {
        Logger.info("Hello Quilt world from {}!", mod.metadata()?.name())
    }

    operator fun invoke(path: String) = Identifier(Namespace, path)
}
