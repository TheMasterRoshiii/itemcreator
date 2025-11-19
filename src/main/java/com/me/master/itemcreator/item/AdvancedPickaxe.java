package com.me.master.itemcreator.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import com.me.master.itemcreator.options.ToolOptions;

public class AdvancedPickaxe extends PickaxeItem {
    public AdvancedPickaxe(Tier material, ToolOptions itemOptions, Item.Properties settings) {
        super(material, itemOptions.getAttackDamage(), itemOptions.getAttackSpeed(), settings);
    }
}
