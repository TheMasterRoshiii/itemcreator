package com.me.master.itemcreator.events;

import com.me.master.itemcreator.commands.ItemCreatorCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandEventHandler {
    
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ItemCreatorCommand.register(event.getDispatcher());
    }
}
