package com.me.master.itemcreator.item;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import com.me.master.itemcreator.options.ToolOptions;

public class AdvancedAxe extends AxeItem {
    public AdvancedAxe(Tier material, ToolOptions itemOptions, Item.Properties settings) {
        super(material, itemOptions.getAttackDamage(), itemOptions.getAttackSpeed(), settings);
    }
}
