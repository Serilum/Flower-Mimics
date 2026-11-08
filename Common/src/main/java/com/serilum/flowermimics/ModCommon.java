package com.serilum.flowermimics;

import com.natamus.collective.config.DuskConfigCustomFile;
import com.natamus.collective.globalcallbacks.GlobalCropCallback;
import com.serilum.flowermimics.config.ConfigHandler;
import com.serilum.flowermimics.data.MimicData;
import com.serilum.flowermimics.events.MimicEvent;
import com.serilum.flowermimics.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();

		load();
		loadEvents();
	}

	private static void load() {
		MimicData.loadDefaultMimicData();
		MimicData.loadDefaultFlowerMimicDrops();

		DuskConfigCustomFile.addIdMap(Reference.MOD_ID, "flower_mimics.txt", "Flower Mimics", "Which mob hides in which flower.", "block", "flowers", "entity_type", "", "single", MimicData::readFlowerMimics, MimicData::writeFlowerMimics);
		DuskConfigCustomFile.addText(Reference.MOD_ID, "flower_item_drops.txt", "Flower Item Drops", "The item a flower's mimic drops, as item NBT.", MimicData::readFlowerItemDrops, MimicData::writeFlowerItemDrops);
	}

	private static void loadEvents() {
		GlobalCropCallback.ON_BONE_MEAL_APPLY.register((Player player, Level level, BlockPos blockPos, BlockState blockState, ItemStack itemStack) -> {
			MimicEvent.onBonemeal(level, blockPos, blockState, itemStack);
			return true;
		});

		GlobalCropCallback.ON_GENERAL_BONE_MEAL_APPLY.register((Level level, BlockPos blockPos, BlockState blockState, ItemStack itemStack) -> {
			MimicEvent.onBonemeal(level, blockPos, blockState, itemStack);
			return true;
		});
	}
}