package com.me.master.itemcreator.loader;

import net.minecraftforge.fml.loading.FMLPaths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextureLoader {
    private static final Path TEXTURE_DIR = FMLPaths.CONFIGDIR.get()
        .resolve("itemcreator")
        .resolve("textures");
    
    public static byte[] loadTexture(String itemName) {
        try {
            Files.createDirectories(TEXTURE_DIR);
            Path texturePath = TEXTURE_DIR.resolve(itemName + ".png");
            
            if (Files.exists(texturePath)) {
                return Files.readAllBytes(texturePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
