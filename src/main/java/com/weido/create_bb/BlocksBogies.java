package com.weido.create_bb;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.weido.create_bb.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlocksBogies implements ModInitializer {
    public static final String MOD_ID = "create_bb";
    public static final String MOD_NAME = "Create: Blocks & Bogies";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static CreateRegistrate REGISTRATE;

    @Override
    public void onInitialize() {
        REGISTRATE = CreateRegistrate.create(MOD_ID);

        BogieStyles.register();
        BogieBlocks.register();
        BogieBlockEntities.register();
        BogiePackets.registerPackets();

        REGISTRATE.register();

        LOGGER.info("{} initialized on Fabric!", MOD_NAME);
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }
}
