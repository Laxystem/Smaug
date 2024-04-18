package quest.laxla.smaug.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import quest.laxla.smaug.Rail;
import quest.laxla.smaug.RailKt;

@Mixin(PoweredRailBlock.class)
public abstract class PoweredRailBlockMixin implements Rail {
	@Shadow
	@Final
	public static BooleanProperty POWERED;

	@SuppressWarnings("AddedMixinMembersNamePattern")
	@Nullable
	@Override
	public Double getMinecartVelocityModification(@NotNull BlockPos pos, @NotNull BlockState blockState, @NotNull AbstractMinecartEntity minecart) {
		if (blockState.isOf(Blocks.POWERED_RAIL) && blockState.get(POWERED)) return RailKt.DefaultVelocityPercentageIncrease;
		else return Rail.super.getMinecartVelocityModification(pos, blockState, minecart);
	}

	@SuppressWarnings("AddedMixinMembersNamePattern")
	@Override
	public boolean isStoppingMinecart(@NotNull BlockPos pos, @NotNull BlockState blockState, @NotNull AbstractMinecartEntity minecart) {
		if (blockState.isOf(Blocks.POWERED_RAIL) && !blockState.get(POWERED)) return true;
		else return Rail.super.isStoppingMinecart(pos, blockState, minecart);
	}
}
