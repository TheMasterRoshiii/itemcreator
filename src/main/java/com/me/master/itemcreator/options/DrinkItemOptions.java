package com.me.master.itemcreator.options;

import net.minecraft.world.food.FoodProperties;
import org.jetbrains.annotations.Nullable;

public class DrinkItemOptions extends ConsumableItemOptions {
    private final boolean removeGlassBottle;
    @Nullable private final String drinkSound;

    private DrinkItemOptions(Builder builder) {
        super(builder);
        this.removeGlassBottle = builder.removeGlassBottle;
        this.drinkSound = builder.drinkSound;
    }

    public boolean isRemoveGlassBottle() { return removeGlassBottle; }
    @Nullable public String getDrinkSound() { return drinkSound; }

    public static Builder builder() { return new Builder(); }

    public static class Builder extends ConsumableItemOptions.Builder {
        private boolean removeGlassBottle = true;
        @Nullable private String drinkSound = null;

        public Builder removeGlassBottle(boolean removeGlassBottle) { this.removeGlassBottle = removeGlassBottle; return this; }
        public Builder drinkSound(@Nullable String drinkSound) { this.drinkSound = drinkSound; return this; }
        public DrinkItemOptions build() { return new DrinkItemOptions(this); }
    }
}
