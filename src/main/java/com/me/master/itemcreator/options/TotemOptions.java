package com.me.master.itemcreator.options;

import org.jetbrains.annotations.Nullable;

public class TotemOptions extends ItemOptions {
    private final boolean destroyOnUse;
    @Nullable private final ItemEffect[] activationEffects;
    @Nullable private final String activationSound;

    protected TotemOptions(Builder builder) {
        super(builder);
        this.destroyOnUse = builder.destroyOnUse;
        this.activationEffects = builder.activationEffects;
        this.activationSound = builder.activationSound;
    }

    public boolean isDestroyOnUse() { return destroyOnUse; }
    @Nullable public ItemEffect[] getActivationEffects() { return activationEffects; }
    @Nullable public String getActivationSound() { return activationSound; }

    public static Builder builder() { return new Builder(); }

    public static class Builder extends ItemOptions.Builder {
        private boolean destroyOnUse = true;
        @Nullable private ItemEffect[] activationEffects = null;
        @Nullable private String activationSound = null;

        public Builder destroyOnUse(boolean destroyOnUse) { this.destroyOnUse = destroyOnUse; return this; }
        public Builder activationEffects(@Nullable ItemEffect[] activationEffects) { this.activationEffects = activationEffects; return this; }
        public Builder activationSound(@Nullable String activationSound) { this.activationSound = activationSound; return this; }
        public TotemOptions build() { return new TotemOptions(this); }
    }
}
