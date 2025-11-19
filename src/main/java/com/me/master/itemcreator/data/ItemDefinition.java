package com.me.master.itemcreator.data;

import com.me.master.itemcreator.types.ItemType;
import net.minecraft.resources.ResourceLocation;

public record ItemDefinition(
    String name,
    ItemType type,
    ItemProperties properties,
    ResourceLocation texture
) {
    public record ItemProperties(
        int maxStackSize,
        int maxDamage,
        boolean fireResistant,
        String rarity
    ) {
        public static ItemProperties defaults() {
            return new ItemProperties(64, 0, false, "COMMON");
        }
    }
}
