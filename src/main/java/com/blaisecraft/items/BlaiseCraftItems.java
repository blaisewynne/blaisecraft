package com.blaisecraft.items;

import com.blaisecraft.BlaiseCraft;
import com.blaisecraft.effects.BlaiseCraftEffects;
import com.blaisecraft.tooltip.BloodItemTooltip;
import com.blaisecraft.tooltip.LabubuItemTooltip;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public class BlaiseCraftItems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BlaiseCraft.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static final Item LABUBU_ITEM = register("labubu_item", LabubuItemTooltip::new, new Item.Settings().maxCount(1).rarity(Rarity.RARE));
    public static final ConsumableComponent BLOOD_CONSUMABLE = ConsumableComponents.food().consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(BlaiseCraftEffects.VAMPIRE, Integer.MAX_VALUE, 0), 1)).build();
    public static final FoodComponent BLOOD_FOOD = new FoodComponent.Builder().alwaysEdible().saturationModifier(8).build();


    public static final Item BLOOD_ITEM = register("blood_item", BloodItemTooltip::new, new Item.Settings().food(BLOOD_FOOD, BLOOD_CONSUMABLE).maxCount(16));

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
                .register((entries) -> {
                    entries.add(LABUBU_ITEM);
                    entries.add(BLOOD_ITEM);
                });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((entries) -> {

                });
    }
}
