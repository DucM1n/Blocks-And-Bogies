package com.weido.create_bb.registry;

import com.weido.create_bb.BlocksBogies;
import com.weido.create_bb.data.packets.BogieMenuPacket;
import com.weido.create_bb.data.packets.BogieStylePacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class BogiePackets {
    public static final ResourceLocation BOGIE_MENU_CHANNEL = BlocksBogies.asResource("bogie_menu");
    public static final ResourceLocation BOGIE_STYLE_CHANNEL = BlocksBogies.asResource("bogie_style");

    public static void registerPackets() {
        ServerPlayNetworking.registerGlobalReceiver(BOGIE_STYLE_CHANNEL, (server, player, handler, buf, responseSender) -> {
            BogieStylePacket packet = new BogieStylePacket(buf);
            server.execute(() -> packet.handleServer(player));
        });

        BlocksBogies.LOGGER.info("Registered server packets for {}", BlocksBogies.MOD_NAME);
    }

    public static void registerClientPackets() {
        ClientPlayNetworking.registerGlobalReceiver(BOGIE_MENU_CHANNEL, (client, handler, buf, responseSender) -> {
            BlockPos pos = buf.readBlockPos();
            client.execute(() -> BogieMenuPacket.handleClient(pos));
        });

        BlocksBogies.LOGGER.info("Registered client packets for {}", BlocksBogies.MOD_NAME);
    }

    public static void sendToPlayer(ServerPlayer player, BogieMenuPacket packet) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        packet.write(buf);
        ServerPlayNetworking.send(player, BOGIE_MENU_CHANNEL, buf);
    }

    public static void sendToServer(BogieStylePacket packet) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        packet.write(buf);
        ClientPlayNetworking.send(BOGIE_STYLE_CHANNEL, buf);
    }

    public static ChannelHelper getChannel() {
        return ChannelHelper.INSTANCE;
    }

    public static class ChannelHelper {
        public static final ChannelHelper INSTANCE = new ChannelHelper();

        public void sendToServer(BogieStylePacket packet) {
            BogiePackets.sendToServer(packet);
        }
    }
}
