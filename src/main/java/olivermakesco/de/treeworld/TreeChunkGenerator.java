package olivermakesco.de.treeworld;
/*A lot of the code in this file is provided from https://github.com/lucaargolo/structure-world, under MPL 2.0*/

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class TreeChunkGenerator extends ChunkGenerator {
    private final String structure;
    private final BlockPos structureOffset;
    private final BlockPos playerSpawnOffset;

    private final BlockState fillmentBlock;
    private final boolean enableTopBedrock;
    private final boolean enableBottomBedrock;
    private final boolean isBedrockFlat;

    public static final Codec<TreeChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter((generator) -> generator.biomeSource),
                    Codec.STRING.stable().fieldOf("structure").forGetter((generator) -> generator.structure),
                    BlockPos.CODEC.fieldOf("structureOffset").forGetter((generator) -> generator.structureOffset),
                    BlockPos.CODEC.fieldOf("playerSpawnOffset").forGetter((generator) -> generator.playerSpawnOffset),
                    BlockState.CODEC.optionalFieldOf("fillmentBlock", Blocks.AIR.getDefaultState()).stable().forGetter((generator) -> generator.fillmentBlock),
                    Codec.BOOL.optionalFieldOf("enableTopBedrock", false).stable().forGetter((generator) -> generator.enableTopBedrock),
                    Codec.BOOL.optionalFieldOf("enableBottomBedrock", false).stable().forGetter((generator) -> generator.enableBottomBedrock),
                    Codec.BOOL.optionalFieldOf("isBedrockFlat", false).stable().forGetter((generator) -> generator.isBedrockFlat)
            ).apply(instance, instance.stable(TreeChunkGenerator::new))
    );

    public TreeChunkGenerator(BiomeSource biomeSource, String structure, BlockPos structureOffset, BlockPos playerSpawnOffset, BlockState fillmentBlock, boolean enableTopBedrock, boolean enableBottomBedrock, boolean isBedrockFlat) {
        super(biomeSource, new StructuresConfig(false));
        this.structure = structure;
        this.structureOffset = structureOffset;
        this.playerSpawnOffset = playerSpawnOffset;
        this.fillmentBlock = fillmentBlock;
        this.enableTopBedrock = enableTopBedrock;
        this.enableBottomBedrock = enableBottomBedrock;
        this.isBedrockFlat = isBedrockFlat;
    }

    public String getStructure() {
        return structure;
    }

    public BlockPos getStructureOffset() {
        return structureOffset;
    }

    public BlockPos getPlayerSpawnOffset() {
        return playerSpawnOffset;
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return this;
    }

    @Override
    public MultiNoiseUtil.MultiNoiseSampler getMultiNoiseSampler() {
        return null;
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk, GenerationStep.Carver generationStep) {

    }

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, Chunk chunk) {
    }

    @Override
    public void populateEntities(ChunkRegion region) {

    }

    @Override
    public int getWorldHeight() {
        return 0;
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender, StructureAccessor structureAccessor, Chunk chunk) {
        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public int getSeaLevel() {
        return 0;
    }

    @Override
    public int getMinimumY() {
        return 0;
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world) {
        return 0;
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world) {
        return new VerticalBlockSample(0, new BlockState[0]);
    }
}
