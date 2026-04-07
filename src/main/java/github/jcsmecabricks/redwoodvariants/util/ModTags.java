package github.jcsmecabricks.redwoodvariants.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> REDWOOD = of("redwood");

        private static TagKey<Block> of(String id) {
            return TagKey.create(Registries.BLOCK, Identifier.withDefaultNamespace(id));
        }
    }
}
