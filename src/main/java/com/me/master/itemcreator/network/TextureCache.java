package com.me.master.itemcreator.network;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import com.me.master.itemcreator.ItemCreator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextureCache {
    private static final Map<String, ResourceLocation> CACHED_TEXTURES = new HashMap<>();
    
    public static void saveTexture(String itemName, byte[] textureData) {
        try {
            NativeImage image = NativeImage.read(new ByteArrayInputStream(textureData));
            DynamicTexture texture = new DynamicTexture(image);
            
            ResourceLocation location = new ResourceLocation(
                ItemCreator.MOD_ID, 
                "textures/item/" + itemName + ".png"
            );
            
            Minecraft.getInstance().getTextureManager().register(location, texture);
            CACHED_TEXTURES.put(itemName, location);
            
            ItemCreator.LOGGER.info("Loaded texture for item: {}", itemName);
        } catch (IOException e) {
            ItemCreator.LOGGER.error("Failed to load texture for {}", itemName, e);
        }
    }
    
    public static ResourceLocation getTexture(String itemName) {
        return CACHED_TEXTURES.get(itemName);
    }
    
    public static void clearCache() {
        CACHED_TEXTURES.clear();
    }
}
