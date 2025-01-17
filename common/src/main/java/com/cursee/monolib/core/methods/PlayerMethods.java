package com.cursee.monolib.core.methods;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class PlayerMethods {

    /** Checks if a player is holding an enchanted item in their main hand */
    public static boolean hasEnchantedMainHand(Player player) {

        if (!player.hasItemInSlot(EquipmentSlot.MAINHAND)) {
            return false;
        }

        ItemStack stack = player.getMainHandItem();

        return !EnchantmentHelper.getEnchantments(stack).isEmpty();
    }

    /** Checks if a player is holding an enchanted item in their offhand */
    public static boolean hasEnchantedOffhand(Player player) {

        if (!player.hasItemInSlot(EquipmentSlot.OFFHAND)) {
            return false;
        }

        ItemStack stack = player.getOffhandItem();

        return !EnchantmentHelper.getEnchantments(stack).isEmpty();
    }

    /** Checks if player's name matches the development use for Fabric, Forge, or NeoForge. */
    public static boolean hasDevelopmentName(Player player) {

        String name = player.getScoreboardName();

        return (Pattern.matches("Dev", name) & name.length() == 3) | (Pattern.matches("Player[0-9]{3}", name));
    }
}
