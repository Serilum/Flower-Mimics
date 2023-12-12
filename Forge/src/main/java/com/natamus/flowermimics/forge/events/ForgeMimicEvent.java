package com.natamus.flowermimics.forge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.natamus.flowermimics.data.MimicData;
import com.natamus.flowermimics.events.MimicEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeMimicEvent {
    @SubscribeEvent
    public void onWorldLoad(LevelEvent.Load e) {
        Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
        if (level == null) {
            return;
        }

		MimicData.attemptMimicConfigProcessing(level);
    }

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		Player player = e.player;
		Level level = player.getLevel();
		if (level.isClientSide || !e.phase.equals(TickEvent.Phase.START)) {
			return;
		}

		MimicEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}

	@SubscribeEvent
	public void mobItemDrop(LivingDropsEvent e) {
		Entity entity = e.getEntity();
		MimicEvent.mobItemDrop(entity.getLevel(), entity, e.getSource());
	}

	@SubscribeEvent
	public void onWorldTick(TickEvent.LevelTickEvent e) {
		Level level = e.level;
		if (level.isClientSide || !e.phase.equals(TickEvent.Phase.START)) {
			return;
		}

		MimicEvent.onLevelTick((ServerLevel)level);
	}

	@SubscribeEvent
	public void onNeighbourNotice(BlockEvent.NeighborNotifyEvent e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
		if (level == null) {
			return;
		}

		MimicEvent.onNeighbourNotice(level, e.getPos(), e.getState(), e.getNotifiedSides(), e.getForceRedstoneUpdate());
	}
}
