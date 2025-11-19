package com.me.master.itemcreator.item;

import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import com.me.master.itemcreator.options.ItemOptions;

public class AdvancedCrossbow extends CrossbowItem {
    private final ItemOptions options;

    public AdvancedCrossbow(Item.Properties settings, ItemOptions options) {
        super(settings);
        this.options = options;
    }

    public ItemOptions getOptions() {
        return options;
    }
}
