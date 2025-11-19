package com.me.master.itemcreator.commands;

import com.me.master.itemcreator.loader.ResourceLoader;
import com.me.master.itemcreator.loader.TextureLoader;
import com.me.master.itemcreator.network.NetworkHandler;
import com.me.master.itemcreator.network.SyncTexturePacket;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import com.google.gson.JsonObject;

import java.util.List;

public class ItemCreatorCommand {
    
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("itemcreator")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("reloadtexture")
                    .executes(ItemCreatorCommand::reloadTextures))
                .then(Commands.literal("reload")
                    .executes(ItemCreatorCommand::reloadAll))
        );
    }
    
    private static int reloadTextures(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack source = ctx.getSource();
        
        List<JsonObject> configs = ResourceLoader.loadItemConfigs();
        int texturesLoaded = 0;
        
        for (JsonObject config : configs) {
            String name = config.get("name").getAsString();
            byte[] textureData = TextureLoader.loadTexture(name);
            
            if (textureData != null) {
                NetworkHandler.sendToAllPlayers(new SyncTexturePacket(name, textureData));
                texturesLoaded++;
            }
        }
        
        source.sendSuccess(() -> Component.literal(
            "§aReloaded " + texturesLoaded + " textures for all players!"), true);
        
        return texturesLoaded;
    }
    
    private static int reloadAll(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack source = ctx.getSource();
        
        source.sendSuccess(() -> Component.literal(
            "§cWarning: Full reload requires server restart!"), false);
        
        return reloadTextures(ctx);
    }
}
