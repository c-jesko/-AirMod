package com.senna.air.EnchantedBowItem;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnchantedBowItem extends BowItem {
    private final Enchantment[] presetEnchantments;

    public EnchantedBowItem(Settings settings, Enchantment... enchantments) {
        super(settings);
        this.presetEnchantments = enchantments;
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);
        // 在创建时添加预设附魔
        if (!world.isClient) {
            for (Enchantment enchantment : presetEnchantments) {
                stack.addEnchantment(enchantment, enchantment.getMaxLevel());
            }
        }

    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        // 返回 false 表示不显示附魔光效
        return false;
    }
}
