package olivermakesco.de.treeworld;

import com.mojang.serialization.Codec;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.GeneratorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Entrypoint implements ModInitializer, ClientModInitializer {
	public static final String modid = "treeworld";
	public static final Logger LOGGER = LogManager.getLogger(modid);

	@Override
	public void onInitialize() {

	}

	@Override @Environment(EnvType.CLIENT)
	public void onInitializeClient() {

		GeneratorType generatorType = new GeneratorType("tree") {
			@Override
			protected ChunkGenerator getChunkGenerator(DynamicRegistryManager registryManager, long seed) {
				BlockState state = Blocks.AIR.getDefaultState();
				return null;
			}
		};
	}

	public static Identifier id(String name) {
		return new Identifier(modid, name);
	}
}
