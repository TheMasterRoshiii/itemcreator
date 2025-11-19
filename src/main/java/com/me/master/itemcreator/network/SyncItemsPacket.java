package com.me.master.itemcreator.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class SyncItemsPacket {
    private final String itemData;
    
    public SyncItemsPacket(String itemData) {
        this.itemData = itemData;
    }
    
    public SyncItemsPacket(FriendlyByteBuf buf) {
        this.itemData = buf.readUtf(32767);
    }
    
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(this.itemData);
    }
    
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Procesar en cliente
        });
        ctx.get().setPacketHandled(true);
    }
}
