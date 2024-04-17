package quest.laxla.smaug.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(Entity.class)
abstract
class EntityMixin {
	@ModifyExpressionValue(method = "handleFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;hasPassengers()Z"))
	protected boolean smaugShouldPropagateFallDamageToPassengers(boolean original) {
		return original;
	}
}
