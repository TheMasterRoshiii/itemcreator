package com.me.master.itemcreator.options;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class ToolOptions {
    @Nullable private final Tier material;
    private final int attackDamage;
    private final float attackSpeed;
    private final Item.Properties settings;
    @Nullable private final ItemEffect[] effects;

    private ToolOptions(Builder builder) {
        this.material = builder.material;
        this.attackDamage = builder.attackDamage;
        this.attackSpeed = builder.attackSpeed;
        this.settings = builder.settings;
        this.effects = builder.effects;
    }

    @Nullable public Tier getMaterial() { return material; }
    public int getAttackDamage() { return attackDamage; }
    public float getAttackSpeed() { return attackSpeed; }
    public Item.Properties getSettings() { return settings; }
    @Nullable public ItemEffect[] getEffects() { return effects; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        @Nullable private Tier material = null;
        private int attackDamage = 0;
        private float attackSpeed = 0.0f;
        private Item.Properties settings = new Item.Properties();
        @Nullable private ItemEffect[] effects = null;

        public Builder material(@Nullable Tier material) { this.material = material; return this; }
        public Builder attackDamage(int attackDamage) { this.attackDamage = attackDamage; return this; }
        public Builder attackSpeed(float attackSpeed) { this.attackSpeed = attackSpeed; return this; }
        public Builder settings(Item.Properties settings) { this.settings = settings; return this; }
        public Builder effects(@Nullable ItemEffect[] effects) { this.effects = effects; return this; }
        public ToolOptions build() { return new ToolOptions(this); }
    }
}
