package com.blaisecraft.group;
import com.blaisecraft.BlaiseCraft;
import com.blaisecraft.items.BlaiseCraftItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BlaiseCraftGroup {
    public static final ItemGroup BLAISE_CRAFT_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(BlaiseCraft.MOD_ID, "blaise_craft_items"), FabricItemGroup.builder()
                    .icon(() -> new ItemStack(BlaiseCraftItems.LABUBU_ITEM))
                    .displayName(Text.translatable("itemGroup.blaisecraft.blaise_craft"))
                    .entries((displayContext, entries) -> {
                        entries.add(BlaiseCraftItems.LABUBU_ITEM);
                        entries.add(BlaiseCraftItems.BLOOD_ITEM);
                        entries.add(BlaiseCraftItems.ANIMATED_INGOT);
                    })
                    .build());
    public static void registerBlaiseCraftGroups() {
        BlaiseCraft.LOGGER.info("Register Groups...");
    }
}