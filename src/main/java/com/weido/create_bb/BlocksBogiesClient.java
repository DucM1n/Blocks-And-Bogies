package com.weido.create_bb;

import com.weido.create_bb.registry.BogiePackets;
import com.weido.create_bb.registry.BogiePartials;
import net.fabricmc.api.ClientModInitializer;

public class BlocksBogiesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BogiePartials.init();
        BogiePackets.registerClientPackets();
    }
}
