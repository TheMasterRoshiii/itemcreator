package com.me.master.itemcreator.api;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface ICustomItem {
    void onItemUsed(ItemStack stack, Level level, Player player);
    boolean canUseItem(ItemStack stack, Player player);
    String getItemCategory();
}
