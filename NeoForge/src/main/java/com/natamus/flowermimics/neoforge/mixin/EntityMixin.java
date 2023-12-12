package com.natamus.flowermimics.neoforge.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Entity.class, priority = 1001)
public class EntityMixin {
	@Inject(method = "thunderHit(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LightningBolt;)V", at = @At(value = "HEAD"), cancellable = true)
	public void thunderHit(ServerLevel serverLevel, LightningBolt lightningBolt, CallbackInfo ci) {
		if (lightningBolt.getTags().contains("visualonly")) {
			ci.cancel();
		}
	}
}