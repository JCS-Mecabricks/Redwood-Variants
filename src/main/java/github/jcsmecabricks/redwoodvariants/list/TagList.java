package github.jcsmecabricks.redwoodvariants.list;

import github.jcsmecabricks.redwoodvariants.RedwoodVariants;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TagList {
    public static class Blocks {
        public static final TagKey<Block> REDWOOD_VARIANTS_TAG = TagKey.create(Registries.BLOCK, RedwoodVariants.id("redwood_tag"));
   }
}
