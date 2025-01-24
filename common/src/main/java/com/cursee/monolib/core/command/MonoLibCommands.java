package com.cursee.monolib.core.command;

import com.cursee.monolib.Constants;
import com.cursee.monolib.core.ConfiguredValues;
import com.cursee.monolib.core.command.hand.HandCommand;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;

public class MonoLibCommands {

    public static void defineCommands(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandContext, Commands.CommandSelection commandEnvironment) {

        // 1.20.1
//        final LiteralArgumentBuilder<CommandSourceStack> root = Commands.literal(Constants.MOD_ID);
//        root.requires(MonoLibCommands::isPlayerOperator).executes(context -> showCommandHelp(context.getSource()));
//        root.then(questionMark());
//        root.then(help());
//        root.then(debug());
//        root.then(hand());

        final LiteralArgumentBuilder<CommandSourceStack> root = LiteralArgumentBuilder.literal(Constants.MOD_ID);
        root.then(HandCommand.build(commandContext));

        commandDispatcher.register(root);
    }

    public static LiteralArgumentBuilder<CommandSourceStack> questionMark() {

        final LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("?");

        command.executes((commandContext -> showCommandHelp(commandContext.getSource())));

        return command;
    }

    public static LiteralArgumentBuilder<CommandSourceStack> help() {

        final LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("help");

        command.executes((commandContext -> showCommandHelp(commandContext.getSource())));

        return command;
    }

    public static LiteralArgumentBuilder<CommandSourceStack> debug() {

        final LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("debug");

        command.executes(context -> {
            Player player = context.getSource().getPlayer();
            if (player == null) return 1;
            ConfiguredValues.ENABLE_DEBUGGING.setValue(!ConfiguredValues.ENABLE_DEBUGGING.get());
            player.sendSystemMessage(Component.literal("MonoLib Debugging: " + String.valueOf(ConfiguredValues.ENABLE_DEBUGGING.get())));
            return 1;
        });

        return command;
    }

//    public static LiteralArgumentBuilder<CommandSourceStack> hand() {
//
//        final LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("hand");
//
//        command.then(HandArgument.arg().executes(HandArgumentHelper::printHeldStack));
//
//        return command;
//    }

    public static LiteralArgumentBuilder<CommandSourceStack> hand(CommandBuildContext context) {

        final LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("hand");

        // command.then(HandArgument.arg().executes(HandArgumentHelper::printHeldStack));

        return command;
    }

    public static int showCommandHelp(CommandSourceStack source) {

        if (isPlayerOperator(source) && source.getPlayer() != null) {
            source.getPlayer().sendSystemMessage(Component.literal("/monolib debug"));
            source.getPlayer().sendSystemMessage(Component.literal(" - Toggles MonoLib's debugging value. (Chat Spam Likely)"));
        }

        return 1;
    }

    public static boolean isPlayerOperator(CommandSourceStack source) {

        boolean isPlayer = source.isPlayer();
        Player player = source.getPlayer();
        MinecraftServer server = source.getServer();

        return isPlayer && player != null && source.hasPermission(server.getOperatorUserPermissionLevel());
    }
}
