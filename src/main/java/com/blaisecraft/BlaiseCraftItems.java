package com.blaisecraft;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
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
    public static final Item LABUBU_ITEM = register("labubu_item", Item::new, new Item.Settings().maxCount(1).rarity(Rarity.RARE));
    public static final Item ARAGORN_SWORD = register("aragorn_sword", Item::new, new Item.Settings().sword(ToolMaterial.DIAMOND, 8f, 3f));

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((entries) -> {
                    entries.add(LABUBU_ITEM);
                    entries.add(ARAGORN_SWORD);
                });
    }

}