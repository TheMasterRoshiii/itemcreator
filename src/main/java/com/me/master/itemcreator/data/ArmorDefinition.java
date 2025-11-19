package com.me.master.itemcreator.data;

public record ArmorDefinition(
    String material,
    String slot,
    int defense,
    float toughness,
    float knockbackResistance,
    int durability
) {
    public static ArmorDefinition defaults(String slot) {
        return new ArmorDefinition("iron", slot, 2, 0.0f, 0.0f, 165);
    }
}
