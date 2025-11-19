package com.me.master.itemcreator.loader;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.me.master.itemcreator.options.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.food.FoodProperties;
import org.jetbrains.annotations.Nullable;

public class ItemOptionsFactory {
    public static ItemOptions fromJson(JsonObject json) {
        Item.Properties properties = new Item.Properties();

        if (json.has("maxStackSize")) {
            properties.stacksTo(json.get("maxStackSize").getAsInt());
        }
        if (json.has("maxDamage")) {
            properties.durability(json.get("maxDamage").getAsInt());
        }
        if (json.has("fireResistant") && json.get("fireResistant").getAsBoolean()) {
            properties.fireResistant();
        }
        if (json.has("rarity")) {
            properties.rarity(Rarity.valueOf(json.get("rarity").getAsString().toUpperCase()));
        }

        ItemEffect[] effects = null;
        if (json.has("effects")) {
            effects = parseEffects(json.getAsJsonArray("effects"));
        }

        return ItemOptions.builder()
                .settings(properties)
                .effects(effects)
                .maxStackSize(json.has("maxStackSize") ? json.get("maxStackSize").getAsInt() : 64)
                .maxDamage(json.has("maxDamage") ? json.get("maxDamage").getAsInt() : 0)
                .fireResistant(json.has("fireResistant") && json.get("fireResistant").getAsBoolean())
                .build();
    }

    public static ConsumableItemOptions consumableFromJson(JsonObject json) {
        ItemOptions base = fromJson(json);
        FoodProperties.Builder foodBuilder = new FoodProperties.Builder();

        if (json.has("nutrition")) {
            foodBuilder.nutrition(json.get("nutrition").getAsInt());
        }
        if (json.has("saturation")) {
            foodBuilder.saturationMod(json.get("saturation").getAsFloat());
        }
        if (json.has("alwaysEdible") && json.get("alwaysEdible").getAsBoolean()) {
            foodBuilder.alwaysEat();
        }
        if (json.has("meat") && json.get("meat").getAsBoolean()) {
            foodBuilder.meat();
        }

        return ConsumableItemOptions.builder()
                .foodProperties(foodBuilder.build())
                .alwaysEdible(json.has("alwaysEdible") && json.get("alwaysEdible").getAsBoolean())
                .useDuration(json.has("useDuration") ? json.get("useDuration").getAsInt() : 32)
                .returnItem(json.has("returnItem") && json.get("returnItem").getAsBoolean())
                .returnItemStack(null)
                .build();
    }

    @Nullable
    private static ItemEffect[] parseEffects(JsonArray effectsArray) {
        if (effectsArray.size() == 0) return null;

        ItemEffect[] effects = new ItemEffect[effectsArray.size()];
        for (int i = 0; i < effectsArray.size(); i++) {
            JsonObject effectJson = effectsArray.get(i).getAsJsonObject();
            effects[i] = ItemEffect.builder()
                    .duration(effectJson.get("duration").getAsInt())
                    .amplifier(effectJson.get("amplifier").getAsInt())
                    .build();
        }
        return effects;
    }
}
