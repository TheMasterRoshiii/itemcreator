package com.me.master.itemcreator.options;

import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class ItemOptions {
    private final Item.Properties settings;
    @Nullable private final ItemEffect[] effects;
    @Nullable private final ItemAttributes[] attributes;
    private final int maxStackSize;
    private final int maxDamage;
    private final boolean fireResistant;

    protected ItemOptions(Builder builder) {
        this.settings = builder.settings;
        this.effects = builder.effects;
        this.attributes = builder.attributes;
        this.maxStackSize = builder.maxStackSize;
        this.maxDamage = builder.maxDamage;
        this.fireResistant = builder.fireResistant;
    }

    public Item.Properties getSettings() { return settings; }
    @Nullable public ItemEffect[] getEffects() { return effects; }
    @Nullable public ItemAttributes[] getAttributes() { return attributes; }
    public int getMaxStackSize() { return maxStackSize; }
    public int getMaxDamage() { return maxDamage; }
    public boolean isFireResistant() { return fireResistant; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Item.Properties settings = new Item.Properties();
        @Nullable private ItemEffect[] effects = null;
        @Nullable private ItemAttributes[] attributes = null;
        private int maxStackSize = 64;
        private int maxDamage = 0;
        private boolean fireResistant = false;

        public Builder settings(Item.Properties settings) { this.settings = settings; return this; }
        public Builder effects(@Nullable ItemEffect[] effects) { this.effects = effects; return this; }
        public Builder attributes(@Nullable ItemAttributes[] attributes) { this.attributes = attributes; return this; }
        public Builder maxStackSize(int maxStackSize) { this.maxStackSize = maxStackSize; return this; }
        public Builder maxDamage(int maxDamage) { this.maxDamage = maxDamage; return this; }
        public Builder fireResistant(boolean fireResistant) { this.fireResistant = fireResistant; return this; }
        public ItemOptions build() { return new ItemOptions(this); }
    }
}
