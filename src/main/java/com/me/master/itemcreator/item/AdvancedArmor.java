package com.me.master.itemcreator.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.Item;
import com.me.master.itemcreator.options.ArmorOptions;

public class AdvancedArmor extends ArmorItem {
    private final ArmorOptions armorOptions;

    public AdvancedArmor(ArmorMaterial material, Type type, Item.Properties settings, ArmorOptions armorOptions) {
        super(material, type, settings);
        this.armorOptions = armorOptions;
    }

    public ArmorOptions getArmorOptions() {
        return armorOptions;
    }
}
