package net.jason13.monolib.methods;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/** Still being used by MobDropsRecipes: Overworld/End, AutoMessage, TimeOnDisplay, and MoreBeautifulTorches. */
@Deprecated(since = "2.0.0", forRemoval = true)
public class BlockMethods {

    @Deprecated(since = "2.0.0", forRemoval = true)
    public static boolean compareBlockToBlock(Block pBlock0, Block pBlock1) {
        if (pBlock0 == null || pBlock1 == null) return false;
        return pBlock0.equals(pBlock1);
    }

    @Deprecated(since = "2.0.0", forRemoval = true)
    public static boolean compareBlockToItem(Block pBlock, Item pItem) {
        if (pBlock == null || pItem == null) return false;
        return compareBlockToBlock(pBlock, Block.byItem(pItem));
    }

    @Deprecated(since = "2.0.0", forRemoval = true)
    public static boolean compareBlockToItemStack(Block pBlock, ItemStack pItemStack) {
        if (pBlock == null || pItemStack == null) return false;
        return compareBlockToItem(pBlock, pItemStack.getItem());
    }

    @Deprecated(since = "2.0.0", forRemoval = true)
    public static boolean compareBlockToBlockState(Block pBlock, BlockState pBlockState) {
        if (pBlock == null || pBlockState == null) return false;
        return compareBlockToBlock(pBlock, pBlockState.getBlock());
    }

    @Deprecated(since = "2.0.0", forRemoval = true)
    public static boolean compareBlockToLevelPosition(Block pBlock, Level pLevel, BlockPos pBlockPos) {
        if (pBlock == null || pLevel == null || pBlockPos == null) return false;
        return compareBlockToBlockState(pBlock, pLevel.getBlockState(pBlockPos));
    }
}