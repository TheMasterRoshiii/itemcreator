package com.me.master.itemcreator.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import com.me.master.itemcreator.options.TotemOptions;

public class AdvancedTotem extends Item {
    private final TotemOptions totemOptions;

    public AdvancedTotem(Item.Properties settings, TotemOptions totemOptions) {
        super(settings);
        this.totemOptions = totemOptions;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.TOOT_HORN;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 1;
    }

    public TotemOptions getTotemOptions() {
        return totemOptions;
    }
}
