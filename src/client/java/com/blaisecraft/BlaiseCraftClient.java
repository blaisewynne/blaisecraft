package com.blaisecraft;

import com.blaisecraft.items.BlaiseCraftItems;
import net.fabricmc.api.ClientModInitializer;

public class BlaiseCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        BlaiseCraftItems.initialize();
	}
}