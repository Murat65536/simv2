package murat.simv2;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SimV2 implements ModInitializer {
	public static final String MOD_ID = "sim-v2";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");
		List.of();
	}
}
