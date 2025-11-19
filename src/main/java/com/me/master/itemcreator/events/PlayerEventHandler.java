package com.me.master.itemcreator.events;

import com.me.master.itemcreator.loader.ResourceLoader;
import com.me.master.itemcreator.loader.TextureLoader;
import com.me.master.itemcreator.network.NetworkHandler;
import com.me.master.itemcreator.network.SyncItemsPacket;
import com.me.master.itemcreator.network.SyncTexturePacket;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

@Mod.EventBusSubscriber
public class PlayerEventHandler {
    private static final Gson GSON = new Gson();
    
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof net.minecraft.server.level.ServerPlayer player) {
            List<JsonObject> configs = ResourceLoader.loadItemConfigs();
            String itemData = GSON.toJson(configs);
            NetworkHandler.sendToPlayer(new SyncItemsPacket(itemData), player);
            

            for (JsonObject config : configs) {
                String name = config.get("name").getAsString();
                byte[] textureData = TextureLoader.loadTexture(name);
                if (textureData != null) {
                    NetworkHandler.sendToPlayer(new SyncTexturePacket(name, textureData), player);
                }
            }
        }
    }
}
