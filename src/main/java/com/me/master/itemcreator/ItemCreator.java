package com.me.master.itemcreator;

import com.me.master.itemcreator.network.NetworkHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ItemCreator.MOD_ID)
public class ItemCreator {
    public static final String MOD_ID = "itemcreator";
    public static final Logger LOGGER = LogManager.getLogger();

    public ItemCreator() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        NetworkHandler.register();


        modEventBus.register(com.me.master.itemcreator.loader.Loader.class);

        LOGGER.info("ItemCreator mod initialized with networking support");
    }
}
