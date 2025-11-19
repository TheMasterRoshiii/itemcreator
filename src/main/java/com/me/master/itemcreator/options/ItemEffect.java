package com.me.master.itemcreator.options;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

public class ItemEffect {
    private final MobEffect effect;
    private final int duration;
    private final int amplifier;
    private final boolean ambient;
    private final boolean visible;

    private ItemEffect(Builder builder) {
        this.effect = builder.effect;
        this.duration = builder.duration;
        this.amplifier = builder.amplifier;
        this.ambient = builder.ambient;
        this.visible = builder.visible;
    }

    public MobEffect getEffect() { return effect; }
    public int getDuration() { return duration; }
    public int getAmplifier() { return amplifier; }
    public boolean isAmbient() { return ambient; }
    public boolean isVisible() { return visible; }

    public MobEffectInstance toInstance() {
        return new MobEffectInstance(effect, duration, amplifier, ambient, visible);
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private MobEffect effect = null;
        private int duration = 0;
        private int amplifier = 0;
        private boolean ambient = false;
        private boolean visible = true;

        public Builder effect(MobEffect effect) { this.effect = effect; return this; }
        public Builder duration(int duration) { this.duration = duration; return this; }
        public Builder amplifier(int amplifier) { this.amplifier = amplifier; return this; }
        public Builder ambient(boolean ambient) { this.ambient = ambient; return this; }
        public Builder visible(boolean visible) { this.visible = visible; return this; }
        public ItemEffect build() { return new ItemEffect(this); }
    }
}
