package com.blaisecraft;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

public class AragornSwordTooltip extends Item {
    public AragornSwordTooltip(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("depreciation")
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("itemTooltip.blaisecraft.aragorn_sword").formatted(Formatting.DARK_GRAY, Formatting.ITALIC));
    }
}
