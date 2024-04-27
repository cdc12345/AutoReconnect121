package autoreconnect.mixin;

import autoreconnect.AutoReconnect;
import autoreconnect.reconnect.RealmsReconnectStrategy;
import com.mojang.realmsclient.dto.RealmsServer;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.realms.RealmsConnect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RealmsConnect.class)
public class RealmsConnectMixin {
    @Inject(at = @At("HEAD"), method = "connect")
    private void connect(RealmsServer server, ServerAddress address, CallbackInfo info) {
        AutoReconnect.getInstance().setReconnectHandler(new RealmsReconnectStrategy(server));
    }
}
