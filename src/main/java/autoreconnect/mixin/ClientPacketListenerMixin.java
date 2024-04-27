package autoreconnect.mixin;

import autoreconnect.AutoReconnect;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundLoginPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Inject(at = @At("TAIL"), method = "handleLogin")
    private void onGameJoin(ClientboundLoginPacket packet, CallbackInfo info) {
        AutoReconnect.getInstance().onGameJoined();
    }
}
