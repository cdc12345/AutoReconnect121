package autoreconnect.reconnect.ui;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class ReconnectButton extends Button {

    protected OnRightPress onRightPress;

    public ReconnectButton(int x, int y, int width, int height, Component message, OnPress onPress, CreateNarration createNarration) {
        super(x, y, width, height, message, onPress, createNarration);
    }

    public void setOnRightPress(OnRightPress onRightPress) {
        this.onRightPress = onRightPress;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 1){
            onRightPress.onRightPress(this);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public interface OnRightPress{
        void onRightPress(Button button);
    }
}
