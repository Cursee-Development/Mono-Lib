package com.cursee.monolib.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import oshi.util.tuples.Triplet;

/** Still being used by EatAnOmelette and GoldenFoods. todo: remove after EatAnOmelette 2.0.0 and GoldenFoods 3.0.0 */
@Deprecated(since = "2.0.0", forRemoval = true)
public class AnvilEventsFabric {

    @Deprecated(since = "2.0.0", forRemoval = true)
    public static final Event<AnvilEventsFabric.Update> UPDATE = EventFactory.createArrayBacked(AnvilEventsFabric.Update.class, callbacks -> (anvilmenu, left, right, output, itemName, baseCost, player) -> {
        for (AnvilEventsFabric.Update callback : callbacks) {
            Triplet<Integer, Integer, ItemStack> triple = callback.onUpdate(anvilmenu, left, right, output, itemName, baseCost, player);
            if (triple != null) return triple;
        }
        return null;
    });

    @FunctionalInterface
    @Deprecated(since = "2.0.0", forRemoval = true)
    public interface Update {
        Triplet<Integer, Integer, ItemStack> onUpdate(AnvilMenu anvilmenu, ItemStack slotLeft, ItemStack slotRight, ItemStack slotOutput, String itemName, int baseCost, Player player);
    }
}

