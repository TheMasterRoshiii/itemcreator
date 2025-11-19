package com.me.master.itemcreator.loader;

import com.google.gson.JsonObject;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ArmorMaterial;

public class ArmorMaterialFactory {
    public static ArmorMaterial fromJson(JsonObject json) {
        if (json.has("material")) {
            return getPresetArmorMaterial(json.get("material").getAsString());
        }
        return ArmorMaterials.IRON;
    }

    private static ArmorMaterial getPresetArmorMaterial(String name) {
        return switch (name.toLowerCase()) {
            case "leather" -> ArmorMaterials.LEATHER;
            case "chainmail" -> ArmorMaterials.CHAIN;
            case "iron" -> ArmorMaterials.IRON;
            case "gold" -> ArmorMaterials.GOLD;
            case "diamond" -> ArmorMaterials.DIAMOND;
            case "netherite" -> ArmorMaterials.NETHERITE;
            case "turtle" -> ArmorMaterials.TURTLE;
            default -> ArmorMaterials.IRON;
        };
    }
}
