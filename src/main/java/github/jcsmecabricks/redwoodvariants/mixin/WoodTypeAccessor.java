package github.jcsmecabricks.redwoodvariants.mixin;

import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WoodType.class)
public interface WoodTypeAccessor {

    @Invoker("register")
    static WoodType redwoodvariants$register(WoodType type) {
        throw new AssertionError();
    }
}