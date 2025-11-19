package com.me.master.itemcreator.item;

import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Item;
import com.me.master.itemcreator.options.ItemOptions;

public class AdvancedShield extends ShieldItem {
    private final ItemOptions shieldOptions;

    public AdvancedShield(Item.Properties settings, ItemOptions shieldOptions) {
        super(settings);
        this.shieldOptions = shieldOptions;
    }

    public ItemOptions getShieldOptions() {
        return shieldOptions;
    }
}
