package quest.laxla.smaug.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.block.BlockState;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quest.laxla.smaug.MinecartsKt;
import quest.laxla.smaug.Rail;
import quest.laxla.smaug.RailKt;
import quest.laxla.smaug.Smaug;

@Mixin(AbstractMinecartEntity.class)
abstract class AbstractMinecartEntityMixin extends EntityMixin {
	//region Faster Airborne Minecarts

	@ModifyExpressionValue(method = "moveOffRail", at = @At(value = "CONSTANT", args = "doubleValue=0.95"))
	double smaug$airborneMinecartSpeed(double original) {
		return Smaug.INSTANCE.getAirborneMinecartSpeedMultiplier();
	}

	//endregion
	//region Fall Damage

	@SuppressWarnings({"ConstantValue", "RedundantSuppression"}) // bugs are weird.
	@Override
	protected boolean smaug$shouldPropagateFallDamageToPassengers(boolean original) {
		return original && MinecartsKt.shouldPropagateFallDamage((AbstractMinecartEntity) (Object) this);
	}

	//endregion
	//region Max Minecart Speed

	@SuppressWarnings("UnreachableCode")
	@ModifyReturnValue(method = "getMaxOffRailSpeed", at = @At("RETURN"))
	double getMaxOffRailSpeed(double original) {
		if (MinecartsKt.shouldApplyMaxSpeedMultiplier((AbstractMinecartEntity) (Object) this))
			return original * Smaug.INSTANCE.getMaxSpeedMultiplier();
		else return original;
	}

	//endregion
	//region Custom Accelerating & Stopping Rails

	@ModifyExpressionValue(method = "moveOnRail", at = @At(value = "CONSTANT", args = "doubleValue=" + RailKt.DefaultVelocityPercentageIncrease))
	double smaug$poweredRailVelocityMultiplier(double original,
											   BlockPos ignored,
											   @NotNull BlockState blockState,
											   @Share("velocityModifier") @NotNull LocalRef<Double> velocityModifierRef) {

		final var velocityModifier = velocityModifierRef.get();

		if (velocityModifier == null) return original;
		else return velocityModifier;
	}

	@ModifyExpressionValue(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 0))
	boolean smaug$recordIsGoldenPoweredRail(boolean original,
											BlockPos ignored,
											@NotNull BlockState blockState,
											@Share("isPoweredRail") @NotNull LocalBooleanRef isPoweredRail) {
		isPoweredRail.set(original);
		return original;
	}

	@SuppressWarnings("UnreachableCode")
	@Inject(method = "moveOnRail", at = @At(value = "CONSTANT", args = "doubleValue=0.0078125", ordinal = 0))
	void smaug$customRailAcceleration(BlockPos pos,
									  BlockState state,
									  CallbackInfo ci,
									  @Local(ordinal = 0) LocalBooleanRef shouldAccelerate,
									  @Local(ordinal = 1) LocalBooleanRef shouldStop,
									  @Share("isPoweredRail") @NotNull LocalBooleanRef isPoweredRail,
									  @Share("velocityModifier") @NotNull LocalRef<Double> velocityModifierRef) {

		if (isPoweredRail.get() || !(state.getBlock() instanceof Rail rail)) return;

		final var minecart = (AbstractMinecartEntity) (Object) this;

		shouldStop.set(rail.isStoppingMinecart(pos, state, minecart));

		final var velocityModifier = rail.getMinecartVelocityModification(pos, state, minecart);

		velocityModifierRef.set(velocityModifier);
		shouldAccelerate.set(velocityModifier != null);
	}

	//endregion
}
