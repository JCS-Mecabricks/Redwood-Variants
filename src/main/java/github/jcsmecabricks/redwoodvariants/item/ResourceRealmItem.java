package github.jcsmecabricks.redwoodvariants.item;

import github.jcsmecabricks.redwoodvariants.block.RedwoodPortalBlock;
import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import github.jcsmecabricks.redwoodvariants.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ResourceRealmItem extends Item {
    public ResourceRealmItem(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if (context.getPlayer() != null) {
            ResourceKey<Level> currentDimension = world.dimension();

            if (currentDimension == ModDimensions.REDWOODDIM_LEVEL_KEY || currentDimension == Level.OVERWORLD) {
                BlockPos[] frameChecks = {
                        pos.offset(-1, 0, 0),
                        pos.offset(1, 0, 0),
                        pos.offset(0, 1, 0),
                        pos.offset(0, 2, 0),
                        pos.offset(0, -1, 0)
                };
                for (BlockPos framePos : frameChecks) {
                    if (((RedwoodPortalBlock) BlockInit.REDWOOD_PORTAL).trySpawnPortal(world, framePos)) {
                        world.playSound(context.getPlayer(), framePos,
                                SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 1.0F, 1.0F);
                        return InteractionResult.CONSUME;
                    }
                }
            }
        }
        return InteractionResult.FAIL;
    }
}
