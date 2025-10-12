package com.blaisecraft.tooltip;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

public class MasterSwordTooltip extends ShieldItem {
    public MasterSwordTooltip(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("depreciation")
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("itemTooltip.blaisecraft.master_sword").formatted(Formatting.LIGHT_PURPLE, Formatting.ITALIC));
    }
}
