package com.me.master.itemcreator.data;

public record ArmorDefinition(
    String material,
    String slot,
    int defense,
    float toughness,
    float knockbackResistance
) {}
