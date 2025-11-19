package com.me.master.itemcreator.events;

import com.me.master.itemcreator.ItemCreator;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ItemCreator.MOD_ID)
public class ServerEventHandler {
    
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        ItemCreator.LOGGER.info("ItemCreator server starting - items loaded");
    }
}
