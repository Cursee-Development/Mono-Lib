package com.cursee.monolib.mixin;

import com.cursee.monolib.core.callback.AnvilEventsFabric;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oshi.util.tuples.Triplet;

@Mixin(value = AnvilMenu.class, priority = 1001)
public abstract class FabricAnvilMenuMixin extends ItemCombinerMenu {

    @Shadow private String itemName;
    @Shadow private int repairItemCountCost;
    @Final @Shadow private DataSlot cost;

    @Inject(method = "createResult()V", at = @At(value= "RETURN"))
    public void onCreateAnvilResult(CallbackInfo info) {

        AnvilMenu instance = (AnvilMenu) (Object) this;
        Container injected$inputSlots = this.inputSlots;

        ItemStack injected$slotLeft = injected$inputSlots.getItem(0);
        ItemStack injected$slotRight = injected$inputSlots.getItem(1);
        ItemStack injected$slotOutput = this.resultSlots.getItem(0);

        int injected$baseCost = injected$slotLeft.getBaseRepairCost() + (injected$slotRight.isEmpty() ? 0 : injected$slotRight.getBaseRepairCost());

        Triplet<Integer, Integer, ItemStack> injected$triple = AnvilEventsFabric.UPDATE.invoker().onUpdate(instance, injected$slotLeft, injected$slotRight, injected$slotOutput, itemName, injected$baseCost, this.player);

        // Still being used by EatAnOmelette and GoldenFoods. todo: remove after EatAnOmelette 2.0.0 and GoldenFoods 3.0.0
        if (injected$triple == null) {
            // see if anything is registered to the deprecated event
            injected$triple = com.cursee.monolib.callback.AnvilEventsFabric.UPDATE.invoker().onUpdate(instance, injected$slotLeft, injected$slotRight, injected$slotOutput, itemName, injected$baseCost, this.player);
        }
        if (injected$triple == null) return;

        if (injected$triple.getA() >= 0) cost.set(injected$triple.getA());

        if (injected$triple.getB() >= 0) repairItemCountCost = injected$triple.getB();

        if (injected$triple.getC() != null) this.resultSlots.setItem(0, injected$triple.getC());
    }

    public FabricAnvilMenuMixin(MenuType<?> menuType, int i, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(menuType, i, inventory, containerLevelAccess);
    }
}
