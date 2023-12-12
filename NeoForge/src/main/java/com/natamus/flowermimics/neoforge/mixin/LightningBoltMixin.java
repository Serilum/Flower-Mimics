package com.natamus.flowermimics.neoforge.mixin;

import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LightningBolt.class, priority = 1001)
public class LightningBoltMixin {
	@Inject(method = "spawnFire(I)V", at = @At(value = "HEAD"), cancellable = true)
	private void spawnFire(int i, CallbackInfo ci) {
		LightningBolt lightningBolt = (LightningBolt)(Object)this;
		if (lightningBolt.getTags().contains("visualonly")) {
			ci.cancel();
		}
	}
}