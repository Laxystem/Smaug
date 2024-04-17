package quest.laxla.smaug.mixin;

import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import quest.laxla.smaug.MinecartsKt;

@Mixin(AbstractMinecartEntity.class)
abstract class AbstractMinecartEntityMixin extends EntityMixin {
	@SuppressWarnings({"ConstantValue", "RedundantSuppression"}) // bugs are weird.
	@Override
	protected boolean smaugShouldPropagateFallDamageToPassengers(boolean original) {
		return original && MinecartsKt.shouldPropagateFallDamage((AbstractMinecartEntity) (Object) this);
	}
}
