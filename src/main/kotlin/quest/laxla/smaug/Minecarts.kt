package quest.laxla.smaug

import net.minecraft.entity.vehicle.AbstractMinecartEntity

@get:JvmName("shouldPropagateFallDamage")
val AbstractMinecartEntity.shouldPropagateFallDamage: Boolean
    get() = supportingBlockPos.map { !world.getBlockState(it).isIn(Smaug.NoPassengerFallDamage) }.orElse(false)

@get:JvmName("shouldApplyMaxSpeedMultiplier")
val AbstractMinecartEntity.shouldApplyMaxSpeedMultiplier: Boolean
    get() = !isOnGround || supportingBlockPos.isPresent &&
        (world.getBlockState(supportingBlockPos.get()).isIn(Smaug.IncreasesMinecartMaxSpeed) ||
            world.getBlockState(supportingBlockPos.get().down()).isIn(Smaug.IncreasesMinecartMaxSpeed))
