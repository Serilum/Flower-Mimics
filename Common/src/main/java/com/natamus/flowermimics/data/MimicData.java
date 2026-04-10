package com.natamus.flowermimics.data;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.util.Pair;
import com.natamus.collective.functions.DataFunctions;
import com.natamus.collective.functions.ItemFunctions;
import com.natamus.flowermimics.util.Reference;
import com.natamus.flowermimics.util.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MimicData {
	public static List<Block> allFlowers = new ArrayList<>();
	private static HashMap<Block, EntityType<?>> flowerToMimic = new HashMap<>();
	private static HashMap<Block, ItemStack> flowerMimicDrops = new HashMap<>();

	public static EntityType<?> getMimicFromFlower(Block block) {
		if (!flowerToMimic.containsKey(block)) {
			return null;
		}
		return flowerToMimic.get(block);
	}

	public static ItemStack getFlowerMimicDrop(Block block) {
		if (!flowerMimicDrops.containsKey(block)) {
			return new ItemStack(Blocks.AIR, 1);
		}
		return flowerMimicDrops.get(block).copy();
	}

	// Default Data
	private static final HashMap<Block, EntityType<?>> defaultFlowerToMimic = new HashMap<>();
	private static final HashMap<Block, Pair<Item, Integer>> defaultFlowerMimicDrops = new HashMap<>();

	public static void loadDefaultMimicData() {
		defaultFlowerToMimic.put(Blocks.ALLIUM, EntityType.ILLUSIONER);
		defaultFlowerToMimic.put(Blocks.AZURE_BLUET, EntityType.WANDERING_TRADER);
		defaultFlowerToMimic.put(Blocks.BLUE_ORCHID, EntityType.DROWNED);
		defaultFlowerToMimic.put(Blocks.CORNFLOWER, EntityType.PHANTOM);
		defaultFlowerToMimic.put(Blocks.DANDELION, EntityType.ZOMBIE_VILLAGER);
		defaultFlowerToMimic.put(Blocks.LILAC, EntityType.SHULKER);
		defaultFlowerToMimic.put(Blocks.LILY_OF_THE_VALLEY, EntityType.GHAST);
		defaultFlowerToMimic.put(Blocks.ORANGE_TULIP, EntityType.SNOW_GOLEM);
		defaultFlowerToMimic.put(Blocks.OXEYE_DAISY, EntityType.STRAY);
		defaultFlowerToMimic.put(Blocks.PEONY, EntityType.VINDICATOR);
		defaultFlowerToMimic.put(Blocks.PINK_TULIP, EntityType.SLIME);
		defaultFlowerToMimic.put(Blocks.POPPY, EntityType.CAVE_SPIDER);
		defaultFlowerToMimic.put(Blocks.RED_TULIP, EntityType.SPIDER);
		defaultFlowerToMimic.put(Blocks.ROSE_BUSH, EntityType.MAGMA_CUBE);
		defaultFlowerToMimic.put(Blocks.SUNFLOWER, EntityType.CREEPER);
		defaultFlowerToMimic.put(Blocks.TORCHFLOWER, EntityType.BLAZE);
		defaultFlowerToMimic.put(Blocks.WHITE_TULIP, EntityType.SKELETON);
		defaultFlowerToMimic.put(Blocks.WITHER_ROSE, EntityType.WITHER_SKELETON);
	}

	public static void loadDefaultFlowerMimicDrops() {
		defaultFlowerMimicDrops.put(Blocks.ALLIUM, new Pair<>(Items.CHORUS_FRUIT, 2));
		defaultFlowerMimicDrops.put(Blocks.AZURE_BLUET, new Pair<>(Items.EMERALD, 1));
		defaultFlowerMimicDrops.put(Blocks.BLUE_ORCHID, new Pair<>(Items.SOUL_LANTERN, 1));
		defaultFlowerMimicDrops.put(Blocks.CORNFLOWER, new Pair<>(Items.FEATHER, 32));
		defaultFlowerMimicDrops.put(Blocks.DANDELION, new Pair<>(Items.GOLDEN_APPLE, 1));
		defaultFlowerMimicDrops.put(Blocks.LILAC, new Pair<>(Items.END_CRYSTAL, 1));
		defaultFlowerMimicDrops.put(Blocks.LILY_OF_THE_VALLEY, new Pair<>(Items.IRON_INGOT, 8));
		defaultFlowerMimicDrops.put(Blocks.ORANGE_TULIP, new Pair<>(Items.POWDER_SNOW_BUCKET, 1));
		defaultFlowerMimicDrops.put(Blocks.OXEYE_DAISY, new Pair<>(Items.SPECTRAL_ARROW, 16));
		defaultFlowerMimicDrops.put(Blocks.PEONY, new Pair<>(Items.CAULDRON, 1));
		defaultFlowerMimicDrops.put(Blocks.PINK_TULIP, new Pair<>(Items.POISONOUS_POTATO, 3));
		defaultFlowerMimicDrops.put(Blocks.POPPY, new Pair<>(Items.COBWEB, 6));
		defaultFlowerMimicDrops.put(Blocks.RED_TULIP, new Pair<>(Items.RABBIT_FOOT, 1));
		defaultFlowerMimicDrops.put(Blocks.ROSE_BUSH, new Pair<>(Items.MAGMA_BLOCK, 14));
		defaultFlowerMimicDrops.put(Blocks.SUNFLOWER, new Pair<>(Items.TNT, 5));
		defaultFlowerMimicDrops.put(Blocks.TORCHFLOWER, new Pair<>(Items.FIRE_CHARGE, 7));
		defaultFlowerMimicDrops.put(Blocks.WHITE_TULIP, new Pair<>(Items.CAKE, 1));
		defaultFlowerMimicDrops.put(Blocks.WITHER_ROSE, new Pair<>(Items.NETHERITE_SCRAP, 2));
	}

	public static EntityType<?> getDefaultMimicFromFlower(Block block) {
		if (!defaultFlowerToMimic.containsKey(block)) {
			return null;
		}
		return defaultFlowerToMimic.get(block);
	}

	// Config File
	private static final String dirPath = DataFunctions.getConfigDirectory() + File.separator + Reference.MOD_ID;
	private static final File dir = new File(dirPath);
	private static final File flowerMimicsFile = new File(dirPath + File.separator + "flower_mimics.txt");
	private static final File flowerItemDropsFile = new File(dirPath + File.separator + "flower_item_drops.txt");

	public static void attemptMimicConfigProcessing(Level level) {
		if (!Variables.processedMimicConfig) {
			try {
				setMimicConfig(level);
				Variables.processedMimicConfig = true;
			} catch (Exception ex) {
				System.out.println("[" + Reference.NAME + "] Error: Unable to generate flower mimic config list.");
				ex.printStackTrace();
			}
		}
	}

	public static void setMimicConfig(Level level) throws IOException {
		Registry<Block> blockRegistry = level.registryAccess().lookupOrThrow(Registries.BLOCK);
		Registry<EntityType<?>> entityTypeRegistry = level.registryAccess().lookupOrThrow(Registries.ENTITY_TYPE);

		allFlowers = new ArrayList<>();

		PrintWriter flowerMimicsWriter = null;
		PrintWriter flowerItemDropsWriter = null;
		if (!dir.isDirectory() || !flowerMimicsFile.isFile() || !flowerItemDropsFile.isFile()) {
			if (dir.mkdirs()) {
				flowerMimicsWriter = new PrintWriter(dirPath + File.separator + "flower_mimics.txt", StandardCharsets.UTF_8);
				flowerItemDropsWriter = new PrintWriter(dirPath + File.separator + "flower_item_drops.txt", StandardCharsets.UTF_8);
			}

			flowerToMimic = new HashMap<>();
			flowerMimicDrops = new HashMap<>();
		}
		else {
			String flowerMimicsContent = new String(Files.readAllBytes(Paths.get(dirPath + File.separator + "flower_mimics.txt")));
			for (String line : flowerMimicsContent.split("\n")) {
				if (line.isBlank()) {
					continue;
				}

				String[] lspl = line.split(";");
				if (lspl.length < 2) {
					Variables.logger.warn("[" + Reference.NAME + "] Unable to parse flower_mimics.txt line: {}", line);
					continue;
				}

				String flowerBlockRlString = lspl[0].strip();
				Identifier flowerBlockIdentifier = Identifier.parse(flowerBlockRlString);

				Optional<Holder.Reference<Block>> flowerBlockOptionalReference = level.registryAccess().lookupOrThrow(BuiltInRegistries.BLOCK.key()).get(flowerBlockIdentifier);
				if (flowerBlockOptionalReference.isEmpty()) {
					Variables.logger.warn("[" + Reference.NAME + "] 1. Unable to find flower '{}' in registry.", flowerBlockRlString);
					continue;
				}

				Block flowerBlock = flowerBlockOptionalReference.get().value();

				String mimicEntityTypeString = lspl[1].strip();
				if (mimicEntityTypeString.isEmpty()) {
					continue;
				}

				Identifier mimicEntityTypeIdentifier = Identifier.parse(mimicEntityTypeString);

				Optional<Holder.Reference<EntityType<?>>> mimicEntityTypeOptionalReference = level.registryAccess().lookupOrThrow(BuiltInRegistries.ENTITY_TYPE.key()).get(mimicEntityTypeIdentifier);
				if (mimicEntityTypeOptionalReference.isEmpty()) {
					Variables.logger.warn("[" + Reference.NAME + "] Unable to find entity type '{}' in registry.", mimicEntityTypeString);
					continue;
				}

				flowerToMimic.put(flowerBlock, mimicEntityTypeOptionalReference.get().value());
			}

			String flowerItemDropsContent = new String(Files.readAllBytes(Paths.get(dirPath + File.separator + "flower_item_drops.txt")));
			for (String line : flowerItemDropsContent.split("\n")) {
				if (line.isBlank()) {
					continue;
				}

				String[] lspl = line.split(";");
				if (lspl.length < 2) {
					Variables.logger.warn("[" + Reference.NAME + "] Unable to parse flower_item_drops.txt line: {}", line);
					continue;
				}

				String flowerBlockRlString = lspl[0].strip();
				Identifier flowerBlockIdentifier = Identifier.parse(flowerBlockRlString);

				Optional<Holder.Reference<Block>> flowerBlockOptionalReference = level.registryAccess().lookupOrThrow(BuiltInRegistries.BLOCK.key()).get(flowerBlockIdentifier);
				if (flowerBlockOptionalReference.isEmpty()) {
					Variables.logger.warn("[" + Reference.NAME + "] 2. Unable to find flower '{}' in registry.", flowerBlockRlString);
					continue;
				}

				String itemStackNBTString = lspl[1].strip();

				try {
					CompoundTag newNBT = TagParser.parseCompoundFully(itemStackNBTString);
					
					ItemStack itemStack = ItemStack.CODEC.parse(level.registryAccess().createSerializationContext(NbtOps.INSTANCE), newNBT).result().orElse(ItemStack.EMPTY);
					if (!itemStack.isEmpty()) {
						flowerMimicDrops.put(flowerBlockOptionalReference.get().value(), itemStack.copy());
					}
				}
				catch (CommandSyntaxException ex) {
					Variables.logger.warn("[" + Reference.NAME + "] Unable to find itemstack from NBT: {}", itemStackNBTString);
				}
			}
		}

		List<String> flowerRLStrings = new ArrayList<>();
		HashMap<String, Block> flowerBlocks = new HashMap<>();

		for (Block block : blockRegistry) {
			if (Util.isFlower(block)) {
				Identifier rl = blockRegistry.getKey(block);
				if (rl == null) {
					continue;
				}

				String flowerRLString = rl.toString();
				flowerRLStrings.add(flowerRLString);
				flowerBlocks.put(flowerRLString, block);
			}
		}

		Collections.sort(flowerRLStrings);

		for (String flowerRLString : flowerRLStrings) {
			Block flowerBlock = flowerBlocks.get(flowerRLString);

			allFlowers.add(flowerBlock);

			if (flowerMimicsWriter != null) {
				String mimicRlString = " ";

				EntityType<?> defaultMimic = getDefaultMimicFromFlower(flowerBlock);
				if (defaultMimic != null) {
					Identifier mimicRl = entityTypeRegistry.getKey(defaultMimic);

					if (mimicRl != null) {
						mimicRlString = mimicRl.toString();
					}
				}

				flowerMimicsWriter.println(flowerRLString + " ; " + mimicRlString);
				flowerToMimic.put(flowerBlock, defaultMimic);
			}

			if (flowerItemDropsWriter != null) {
				ItemStack defaultFlowerMimicDropStack = new ItemStack(Blocks.AIR, 1);

				if (defaultFlowerMimicDrops.containsKey(flowerBlock)) {
					Pair<Item, Integer> itemPair = defaultFlowerMimicDrops.get(flowerBlock);
					defaultFlowerMimicDropStack = new ItemStack(itemPair.getFirst(), itemPair.getSecond());
				}

				String nBTString = ItemFunctions.getNBTStringFromItemStack(level, defaultFlowerMimicDropStack);

				flowerItemDropsWriter.println(flowerRLString + " ; " + nBTString);
				flowerMimicDrops.put(flowerBlock, defaultFlowerMimicDropStack.copy());
			}
		}

		if (flowerMimicsWriter != null) {
			flowerMimicsWriter.close();
		}
		if (flowerItemDropsWriter != null) {
			flowerItemDropsWriter.close();
		}
	}
}
