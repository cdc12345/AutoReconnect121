package autoreconnect.mixin;

import autoreconnect.AutoReconnect;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({JoinMultiplayerScreen.class})
public class MultiplayerScreenMixin extends Screen {

    protected MultiplayerScreenMixin(Component component) {
        super(component);
    }

    @Inject(method = "init",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/multiplayer/JoinMultiplayerScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;",shift = At.Shift.AFTER,ordinal = 2))
    private void init(CallbackInfo ci){
        var recon = this.addRenderableWidget(Button.builder(Component.translatable("text.autoreconnect.disconnect.reconnect"),(button)->{
            AutoReconnect.getInstance().reconnect();
        }).width(100).pos(5,5).build());

        recon.active = !AutoReconnect.getInstance().isNullReconnect();
    }

}
