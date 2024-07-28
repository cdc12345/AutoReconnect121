package autoreconnect.mixin;

import autoreconnect.AutoReconnect;
import autoreconnect.reconnect.ui.ReconnectButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    @Redirect(method = "createNormalMenuOptions",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;build()Lnet/minecraft/client/gui/components/Button;",ordinal = 1))
    public Button buildNewButton(Button.Builder builder){
        ReconnectButton reconnectButton = new ReconnectButton(builder.x, builder.y, builder.width, builder.height, builder.message, builder.onPress, builder.createNarration);
        reconnectButton.setTooltip(Tooltip.create(Component.translatable("text.autoreconnect.title.rReconnect")));
        reconnectButton.setOnRightPress((button) -> {
            var mc = Minecraft.getInstance();
            mc.setScreen(new JoinMultiplayerScreen(new JoinMultiplayerScreen(new TitleScreen())));
            if (AutoReconnect.getInstance().isNullReconnect()){
                ServerData server = new ServerData("wycraft","main.wycraft.cn", ServerData.Type.OTHER);
                ConnectScreen.startConnecting(mc.screen,mc , ServerAddress.parseString(server.ip), server, false, null);
                return;
            }
            AutoReconnect.getInstance().reconnect();
        });
        return reconnectButton;
    }
}
