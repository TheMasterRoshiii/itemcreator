package com.me.master.itemcreator.loader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ResourceLoader {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Path CONFIG_DIR = FMLPaths.CONFIGDIR.get().resolve("itemcreator");

    public static List<JsonObject> loadItemConfigs() {
        List<JsonObject> configs = new ArrayList<>();

        try {
            Files.createDirectories(CONFIG_DIR);

            if (!Files.exists(CONFIG_DIR.resolve("items.json"))) {
                createDefaultConfig();
            }

            String content = Files.readString(CONFIG_DIR.resolve("items.json"));
            JsonArray array = JsonParser.parseString(content).getAsJsonArray();

            for (JsonElement element : array) {
                configs.add(element.getAsJsonObject());
            }

            LOGGER.info("Loaded {} item configurations", configs.size());
        } catch (IOException e) {
            LOGGER.error("Failed to load item configurations", e);
        }

        return configs;
    }

    private static void createDefaultConfig() throws IOException {
        JsonObject example = new JsonObject();
        example.addProperty("name", "example_pickaxe");
        example.addProperty("type", "pickaxe");
        example.addProperty("material", "diamond");
        example.addProperty("attackDamage", 5);
        example.addProperty("attackSpeed", -2.8f);

        JsonArray array = new JsonArray();
        array.add(example);

        Files.writeString(CONFIG_DIR.resolve("items.json"), array.toString());
    }
}
