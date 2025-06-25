package github.jcsmecabricks.redwoodvariants.util;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> REDWOOD = of("redwood");

        private static TagKey<Block> of(String id) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.ofVanilla(id));
        }
    }
}
