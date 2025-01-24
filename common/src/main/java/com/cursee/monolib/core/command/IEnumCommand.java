package com.cursee.monolib.core.command;

import com.mojang.brigadier.Command;
import net.minecraft.commands.CommandSourceStack;

/**
 * Adapted from Darkhax's <a href="https://github.com/Darkhax-Minecraft/Bookshelf">Bookshelf</a>
 * Allows an enum to be used as a branching command path.
 */
public interface IEnumCommand extends Command<CommandSourceStack> {

    /**
     * Gets the name of the command. This must be unique for each enum value.
     *
     * @return The name of the command.
     */
    String getCommandName();

    /**
     * Gets the required permission level to perform the command.
     *
     * @return The required permission level.
     */
    default PermissionLevel requiredPermissionLevel() {
        return PermissionLevel.PLAYER;
    }
}
