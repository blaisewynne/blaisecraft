package com.blaisecraft;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlaiseCraft implements ModInitializer {
	public static final String MOD_ID = "blaisecraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        BlaiseCraftItems.initialize();

		LOGGER.info("Hello Fabric world!");
	}
}