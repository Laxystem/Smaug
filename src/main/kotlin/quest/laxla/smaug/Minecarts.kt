package quest.laxla.smaug

import net.minecraft.entity.vehicle.AbstractMinecartEntity

@get:JvmName("shouldPropagateFallDamage")
val AbstractMinecartEntity.shouldPropagateFallDamage: Boolean
    get() = supportingBlockPos.map { !world.getBlockState(it).isIn(Smaug.NoPassengerFallDamage) }.orElse(false)
