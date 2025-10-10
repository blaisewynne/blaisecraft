package com.blaisecraft;

import net.fabricmc.api.ClientModInitializer;

public class BlaiseCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        BlaiseCraftItems.initialize();
	}
}