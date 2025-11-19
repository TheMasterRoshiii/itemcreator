package com.me.master.itemcreator.options;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import org.jetbrains.annotations.Nullable;

public class ConsumableItemOptions extends ItemOptions {
    private final FoodProperties foodProperties;
    private final boolean alwaysEdible;
    private final int useDuration;
    private final boolean returnItem;
    @Nullable private final Item returnItemStack;

    private ConsumableItemOptions(Builder builder) {
        super(builder);
        this.foodProperties = builder.foodProperties;
        this.alwaysEdible = builder.alwaysEdible;
        this.useDuration = builder.useDuration;
        this.returnItem = builder.returnItem;
        this.returnItemStack = builder.returnItemStack;
    }

    public FoodProperties getFoodProperties() { return foodProperties; }
    public boolean isAlwaysEdible() { return alwaysEdible; }
    public int getUseDuration() { return useDuration; }
    public boolean isReturnItem() { return returnItem; }
    @Nullable public Item getReturnItemStack() { return returnItemStack; }

    public static Builder builder() { return new Builder(); }

    public static class Builder extends ItemOptions.Builder {
        private FoodProperties foodProperties = new FoodProperties.Builder().build();
        private boolean alwaysEdible = false;
        private int useDuration = 32;
        private boolean returnItem = false;
        @Nullable private Item returnItemStack = null;

        public Builder foodProperties(FoodProperties foodProperties) { this.foodProperties = foodProperties; return this; }
        public Builder alwaysEdible(boolean alwaysEdible) { this.alwaysEdible = alwaysEdible; return this; }
        public Builder useDuration(int useDuration) { this.useDuration = useDuration; return this; }
        public Builder returnItem(boolean returnItem) { this.returnItem = returnItem; return this; }
        public Builder returnItemStack(@Nullable Item returnItemStack) { this.returnItemStack = returnItemStack; return this; }
        public ConsumableItemOptions build() { return new ConsumableItemOptions(this); }
    }
}
