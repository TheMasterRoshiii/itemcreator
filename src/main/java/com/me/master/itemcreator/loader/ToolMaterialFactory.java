package com.me.master.itemcreator.loader;

import com.google.gson.JsonObject;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.Tier;

public class ToolMaterialFactory {
    public static Tier fromJson(JsonObject json) {
        if (json.has("material")) {
            return getPresetTier(json.get("material").getAsString());
        }
        return Tiers.IRON;
    }

    private static Tier getPresetTier(String name) {
        return switch (name.toLowerCase()) {
            case "wood" -> Tiers.WOOD;
            case "stone" -> Tiers.STONE;
            case "iron" -> Tiers.IRON;
            case "gold" -> Tiers.GOLD;
            case "diamond" -> Tiers.DIAMOND;
            case "netherite" -> Tiers.NETHERITE;
            default -> Tiers.IRON;
        };
    }
}
