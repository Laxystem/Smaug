package quest.laxla.smaug.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
abstract
class EntityMixin {
	@ModifyExpressionValue(method = "handleFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;hasPassengers()Z"))
	protected boolean smaug$shouldPropagateFallDamageToPassengers(boolean original) {
		return original;
	}
}
