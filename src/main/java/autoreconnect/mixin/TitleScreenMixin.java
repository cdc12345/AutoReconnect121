package autoreconnect.mixin;

import autoreconnect.AutoReconnect;
import autoreconnect.reconnect.ui.ReconnectButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Redirect(method = "createNormalMenuOptions",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;build()Lnet/minecraft/client/gui/components/Button;",ordinal = 1))
    public Button buildNewButton(Button.Builder builder){
        ReconnectButton reconnectButton = new ReconnectButton(builder.x, builder.y, builder.width, builder.height, builder.message, builder.onPress, builder.createNarration);
        reconnectButton.setTooltip(builder.tooltip);
        reconnectButton.setOnRightPress((button) -> AutoReconnect.getInstance().reconnect());
        return reconnectButton;
    }
}
