package com.me.master.itemcreator.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class SyncTexturePacket {
    private final String itemName;
    private final byte[] textureData;
    
    public SyncTexturePacket(String itemName, byte[] textureData) {
        this.itemName = itemName;
        this.textureData = textureData;
    }
    
    public SyncTexturePacket(FriendlyByteBuf buf) {
        this.itemName = buf.readUtf();
        int length = buf.readInt();
        this.textureData = new byte[length];
        buf.readBytes(this.textureData);
    }
    
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(this.itemName);
        buf.writeInt(this.textureData.length);
        buf.writeBytes(this.textureData);
    }
    
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Guardar textura en cache del cliente
            TextureCache.saveTexture(itemName, textureData);
        });
        ctx.get().setPacketHandled(true);
    }
}
