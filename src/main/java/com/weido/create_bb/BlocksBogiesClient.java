package com.weido.create_bb;

import com.weido.create_bb.data.compat.ForgeObjLoader;
import com.weido.create_bb.registry.BogiePackets;
import com.weido.create_bb.registry.BogiePartials;
import io.github.fabricators_of_create.porting_lib.models.geometry.RegisterGeometryLoadersCallback;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.resources.ResourceLocation;

public class BlocksBogiesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RegisterGeometryLoadersCallback.EVENT.register(loaders -> {
            loaders.put(new ResourceLocation("forge", "obj"), ForgeObjLoader.INSTANCE);
            loaders.put(new ResourceLocation("porting_lib", "obj"), ForgeObjLoader.INSTANCE);
        });

        BogiePartials.init();
        BogiePackets.registerClientPackets();
    }
}
