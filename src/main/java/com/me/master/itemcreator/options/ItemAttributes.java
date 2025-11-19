package com.me.master.itemcreator.options;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import java.util.UUID;

public class ItemAttributes {
    private final Attribute attribute;
    private final UUID uuid;
    private final String name;
    private final double amount;
    private final AttributeModifier.Operation operation;
    private final EquipmentSlot slot;

    private ItemAttributes(Builder builder) {
        this.attribute = builder.attribute;
        this.uuid = builder.uuid;
        this.name = builder.name;
        this.amount = builder.amount;
        this.operation = builder.operation;
        this.slot = builder.slot;
    }

    public Attribute getAttribute() { return attribute; }
    public UUID getUuid() { return uuid; }
    public String getName() { return name; }
    public double getAmount() { return amount; }
    public AttributeModifier.Operation getOperation() { return operation; }
    public EquipmentSlot getSlot() { return slot; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Attribute attribute = null;
        private UUID uuid = UUID.randomUUID();
        private String name = "";
        private double amount = 0.0;
        private AttributeModifier.Operation operation = AttributeModifier.Operation.ADDITION;
        private EquipmentSlot slot = EquipmentSlot.MAINHAND;

        public Builder attribute(Attribute attribute) { this.attribute = attribute; return this; }
        public Builder uuid(UUID uuid) { this.uuid = uuid; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder amount(double amount) { this.amount = amount; return this; }
        public Builder operation(AttributeModifier.Operation operation) { this.operation = operation; return this; }
        public Builder slot(EquipmentSlot slot) { this.slot = slot; return this; }
        public ItemAttributes build() { return new ItemAttributes(this); }
    }
}
