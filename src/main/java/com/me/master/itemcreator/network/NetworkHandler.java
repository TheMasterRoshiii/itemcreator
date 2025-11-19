package com.me.master.itemcreator.network;

import com.me.master.itemcreator.ItemCreator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(ItemCreator.MOD_ID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );
    
    private static int packetId = 0;
    
    public static void register() {
        INSTANCE.messageBuilder(SyncItemsPacket.class, packetId++)
            .encoder(SyncItemsPacket::encode)
            .decoder(SyncItemsPacket::new)
            .consumerMainThread(SyncItemsPacket::handle)
            .add();
            
        INSTANCE.messageBuilder(SyncTexturePacket.class, packetId++)
            .encoder(SyncTexturePacket::encode)
            .decoder(SyncTexturePacket::new)
            .consumerMainThread(SyncTexturePacket::handle)
            .add();
    }
    
    public static void sendToPlayer(Object packet, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }
    
    public static void sendToAllPlayers(Object packet) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), packet);
    }
}
