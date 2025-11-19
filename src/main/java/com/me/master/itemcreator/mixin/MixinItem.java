package com.me.master.itemcreator.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.me.master.itemcreator.api.ICustomItem;

@Mixin(Item.class)
public class MixinItem {
    
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onUse(net.minecraft.world.level.Level level, Player player, 
                      InteractionHand hand, CallbackInfoReturnable<net.minecraft.world.InteractionResultHolder<ItemStack>> cir) {
        ItemStack stack = player.getItemInHand(hand);
        
        if (stack.getItem() instanceof ICustomItem customItem) {
            if (!customItem.canUseItem(stack, player)) {
                cir.setReturnValue(net.minecraft.world.InteractionResultHolder.fail(stack));
                return;
            }
            customItem.onItemUsed(stack, level, player);
        }
    }
}
