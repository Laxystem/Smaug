package quest.laxla.smaug

import net.minecraft.block.BlockState
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.util.math.BlockPos

/**
 * In vanilla, [powered rails][net.minecraft.block.PoweredRailBlock] increase passing minecarts' speed by this percentage when turned on.
 *
 * @see Rail.getMinecartVelocityModification
 */
const val DefaultVelocityPercentageIncrease = 0.06

/**
 * In vanilla, [powered rails][net.minecraft.block.PoweredRailBlock] multiply passing minecarts' speed by this value when turned off.
 *
 * @see Rail.isStoppingMinecart
 */
const val StoppingRailVelocityMultiplier = 0.5

interface Rail {
    /**
     * Minecarts that pass on this rail will have their [velocity][net.minecraft.entity.Entity.velocity] increased by this percentage of their current speed every tick.
     *
     * @see DefaultVelocityPercentageIncrease
     */
    fun getMinecartVelocityModification(pos: BlockPos, blockState: BlockState, minecart: AbstractMinecartEntity): Double? = null

    /**
     * Should minecarts that pass on this rail stop?
     *
     * @see StoppingRailVelocityMultiplier
     */
    fun isStoppingMinecart(pos: BlockPos, blockState: BlockState, minecart: AbstractMinecartEntity): Boolean = false
}
