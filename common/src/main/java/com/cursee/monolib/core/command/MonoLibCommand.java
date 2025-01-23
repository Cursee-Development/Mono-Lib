package com.cursee.monolib.core.command;

import com.cursee.monolib.Constants;
import com.cursee.monolib.core.ConfiguredValues;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;

public class MonoLibCommand {

    public static void defineCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context, Commands.CommandSelection environment) {
        dispatcher.register(

            Commands.literal(Constants.MOD_ID)

            .requires(MonoLibCommand::isPlayerOperator)

            // /monolib
            .executes((commandContext) -> showCommandHelp(commandContext.getSource()))

            // /monolib ?
            // /monolib help
            .then(Commands.literal("?").executes((commandContext -> showCommandHelp(commandContext.getSource()))))
            .then(Commands.literal("help").executes((commandContext -> showCommandHelp(commandContext.getSource()))))

            // /monolib debug
            .then(Commands.literal("debug")
                .executes(commandContext -> {
                    Player player = commandContext.getSource().getPlayer();
                    if (player == null) return 1;

                    ConfiguredValues.ENABLE_DEBUGGING.setValue(!ConfiguredValues.ENABLE_DEBUGGING.get());
                    player.sendSystemMessage(Component.literal("MonoLib Debugging: " + String.valueOf(ConfiguredValues.ENABLE_DEBUGGING.get())));

                    return 1;
                })
            )

            // /monolib reset headache
            // .then(Commands.literal("reset")
            // .then(Commands.literal("headache")
            //     .executes(commandContext -> {
            //         Player player = commandContext.getSource().getPlayer();
            //         if (player == null) return 1;
            //         player.sendSystemMessage(Component.literal("reset headache???"));
            //
            //         return 1;
            //     })
            // ))
        );
    }

    public static int showCommandHelp(CommandSourceStack sourceStack) {

        if (isPlayerOperator(sourceStack) && sourceStack.getPlayer() != null) {
            sourceStack.getPlayer().sendSystemMessage(Component.literal("/monolib debug"));
            sourceStack.getPlayer().sendSystemMessage(Component.literal("    Used to toggle MonoLib's common debugging value during runtime."));
        }

        return 1;
    }

    public static boolean isPlayerOperator(CommandSourceStack sourceStack) {

        boolean isPlayer = sourceStack.isPlayer();
        Player player = sourceStack.getPlayer();
        MinecraftServer server = sourceStack.getServer();

        return isPlayer && player != null && sourceStack.hasPermission(server.getOperatorUserPermissionLevel());
    }
}
