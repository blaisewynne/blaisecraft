package com.blaisecraft.items;

import com.blaisecraft.BlaiseCraft;
import com.blaisecraft.effects.BlaiseCraftEffects;
import com.blaisecraft.entity.BlaiseCraftEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public class BlaiseCraftItems {
    private static Item register(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(BlaiseCraft.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BlaiseCraft.MOD_ID, name)))));
    }
    public static final Item LABUBU_ITEM = register("labubu_item", settings -> new Item(settings.maxCount(1).rarity(Rarity.EPIC)));
    public static final ConsumableComponent BLOOD_CONSUMABLE = ConsumableComponents.food().consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(BlaiseCraftEffects.VAMPIRE, Integer.MAX_VALUE, 0), 1)).build();
    public static final FoodComponent BLOOD_FOOD = new FoodComponent.Builder().alwaysEdible().saturationModifier(8).build();
    public static final Item BLOOD_ITEM = register("blood_item", settings -> new Item(settings.food(BLOOD_FOOD, BLOOD_CONSUMABLE).maxCount(16)));
    public static final Item ANIMATED_SCRAP = register("animated_scrap", settings -> new Item(settings.maxCount(64)));
    public static final Item ANIMATED_INGOT = register("animated_ingot", settings -> new Item(settings.maxCount(64)));

    public static final Item VAMPIRE_SPAWN_EGG = register("vampire_spawn_egg",
            setting -> new SpawnEggItem(setting.spawnEgg(BlaiseCraftEntities.VAMPIRE)));

    public static final Item WEREWOLF_SPAWN_EGG = register("werewolf_spawn_egg",
            setting -> new SpawnEggItem(setting.spawnEgg(BlaiseCraftEntities.WEREWOLF)));

    public static final Item ANIMATED_ARMOUR_SPAWN_EGG = register("animated_armour_spawn_egg",
            setting -> new SpawnEggItem(setting.spawnEgg(BlaiseCraftEntities.ANIMATED_ARMOUR)));

    public static final Item BEHOLDER_SPAWN_EGG = register("beholder_spawn_egg",
            setting -> new SpawnEggItem(setting.spawnEgg(BlaiseCraftEntities.BEHOLDER)));

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
                .register((entries) -> {
                    entries.add(LABUBU_ITEM);
                    entries.add(BLOOD_ITEM);
                    entries.add(ANIMATED_SCRAP);
                    entries.add(ANIMATED_INGOT);
                });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((entries) -> {

                });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS)
                .register(entries -> {
                    entries.add(VAMPIRE_SPAWN_EGG);
                    entries.add(WEREWOLF_SPAWN_EGG);
                    entries.add(ANIMATED_ARMOUR_SPAWN_EGG);
                    entries.add(BEHOLDER_SPAWN_EGG);
                });
    }
}
