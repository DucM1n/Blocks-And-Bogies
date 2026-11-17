package com.weido.create_bb.data.packets;

import com.weido.create_bb.data.menu.BogieStyleSelectionScreen;
import net.createmod.catnip.gui.ScreenOpener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class BogieMenuPacket {
    private final BlockPos pos;

    public BogieMenuPacket(BlockPos pos) {
        this.pos = pos;
    }

    public BogieMenuPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public BlockPos getPos() {
        return pos;
    }

    @Environment(EnvType.CLIENT)
    public static void handleClient(BlockPos pos) {
        ScreenOpener.open(new BogieStyleSelectionScreen(pos));
    }
}
