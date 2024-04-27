package autoreconnect.reconnect;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;

public class MultiplayerReconnectStrategy extends ReconnectStrategy {
    private final ServerData serverData;

    public MultiplayerReconnectStrategy(ServerData serverData) {
        this.serverData = serverData;
    }

    @Override
    public String getName() {
        return serverData.name;
    }

    /**
     * @see net.minecraft.client.gui.screens.ConnectScreen#connect(Minecraft, ServerAddress, ServerData)
     */
    @Override
    public void reconnect() {
        ConnectScreen.startConnecting(
                new JoinMultiplayerScreen(new TitleScreen()),
                Minecraft.getInstance(),
                ServerAddress.parseString(serverData.ip),
                serverData,
                false
        );
    }
}
