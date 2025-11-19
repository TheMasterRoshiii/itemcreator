package com.me.master.itemcreator.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.entity.ai.attributes.Attributes;
import com.me.master.itemcreator.options.ToolOptions;

public class AdvancedTrident extends TridentItem {
    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public AdvancedTrident(Item.Properties settings, ToolOptions itemOptions) {
        super(settings);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                BASE_ATTACK_DAMAGE_UUID, "Tool modifier",
                (double)itemOptions.getAttackDamage(),
                AttributeModifier.Operation.ADDITION
        ));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                BASE_ATTACK_SPEED_UUID, "Tool modifier",
                (double)itemOptions.getAttackSpeed(),
                AttributeModifier.Operation.ADDITION
        ));
        this.attributeModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot, stack);
    }
}
