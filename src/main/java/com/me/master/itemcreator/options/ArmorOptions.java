package com.me.master.itemcreator.options;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.EquipmentSlot;
import org.jetbrains.annotations.Nullable;

public class ArmorOptions {
    @Nullable private final ArmorMaterial material;
    private final EquipmentSlot slot;
    private final int defense;
    private final float toughness;
    private final float knockbackResistance;
    private final Item.Properties settings;
    @Nullable private final ItemEffect[] effects;

    private ArmorOptions(Builder builder) {
        this.material = builder.material;
        this.slot = builder.slot;
        this.defense = builder.defense;
        this.toughness = builder.toughness;
        this.knockbackResistance = builder.knockbackResistance;
        this.settings = builder.settings;
        this.effects = builder.effects;
    }

    @Nullable public ArmorMaterial getMaterial() { return material; }
    public EquipmentSlot getSlot() { return slot; }
    public int getDefense() { return defense; }
    public float getToughness() { return toughness; }
    public float getKnockbackResistance() { return knockbackResistance; }
    public Item.Properties getSettings() { return settings; }
    @Nullable public ItemEffect[] getEffects() { return effects; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        @Nullable private ArmorMaterial material = null;
        private EquipmentSlot slot = EquipmentSlot.HEAD;
        private int defense = 0;
        private float toughness = 0.0f;
        private float knockbackResistance = 0.0f;
        private Item.Properties settings = new Item.Properties();
        @Nullable private ItemEffect[] effects = null;

        public Builder material(@Nullable ArmorMaterial material) { this.material = material; return this; }
        public Builder slot(EquipmentSlot slot) { this.slot = slot; return this; }
        public Builder defense(int defense) { this.defense = defense; return this; }
        public Builder toughness(float toughness) { this.toughness = toughness; return this; }
        public Builder knockbackResistance(float knockbackResistance) { this.knockbackResistance = knockbackResistance; return this; }
        public Builder settings(Item.Properties settings) { this.settings = settings; return this; }
        public Builder effects(@Nullable ItemEffect[] effects) { this.effects = effects; return this; }
        public ArmorOptions build() { return new ArmorOptions(this); }
    }
}
