package github.jcsmecabricks.redwoodvariants.item;

import github.jcsmecabricks.redwoodvariants.block.RedwoodPortalBlock;
import github.jcsmecabricks.redwoodvariants.init.BlockInit;
import github.jcsmecabricks.redwoodvariants.world.dimension.ModDimensions;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ResourceRealmItem extends Item {
    public ResourceRealmItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();

        if (context.getPlayer() != null) {
            RegistryKey<World> currentDimension = world.getRegistryKey();

            if (currentDimension == ModDimensions.REDWOODDIM_LEVEL_KEY || currentDimension == World.OVERWORLD) {
                BlockPos[] frameChecks = {
                        pos.add(-1, 0, 0),
                        pos.add(1, 0, 0),
                        pos.add(0, 1, 0),
                        pos.add(0, 2, 0),
                        pos.add(0, -1, 0)
                };
                for (BlockPos framePos : frameChecks) {
                    if (((RedwoodPortalBlock) BlockInit.REDWOOD_PORTAL).trySpawnPortal(world, framePos)) {
                        world.playSound(context.getPlayer(), framePos,
                                SoundEvents.BLOCK_PORTAL_TRIGGER, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        return ActionResult.CONSUME;
                    }
                }
            }
        }
        return ActionResult.FAIL;
    }
}
