package autoreconnect.reconnect;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.TransferState;
import net.minecraft.client.multiplayer.resolver.ServerAddress;

public class MultiplayerReconnectStrategy extends ReconnectStrategy {
    private static MultiplayerReconnectStrategy instance;

    public static MultiplayerReconnectStrategy buildManager(ServerData serverData , TransferState transferState){
        if (instance == null){
            return instance = new MultiplayerReconnectStrategy(serverData,transferState);
        } else {
            instance.serverData = serverData;
            instance.transferState = transferState;
            return instance;
        }
    }

    private ServerData serverData;
    private TransferState transferState;

    private MultiplayerReconnectStrategy(ServerData serverData, TransferState transferState) {
        this.serverData = serverData;
        this.transferState = transferState;
    }

    @Override
    public String getName() {
        return serverData.name;
    }

    /**
     * @see net.minecraft.client.gui.screens.ConnectScreen#startConnecting(Screen, Minecraft, ServerAddress, ServerData, boolean, TransferState) (Minecraft, ServerAddress, ServerData)
     */
    @Override
    public void reconnect() {
        ConnectScreen.startConnecting(
                new JoinMultiplayerScreen(new TitleScreen()),
                Minecraft.getInstance(),
                ServerAddress.parseString(serverData.ip),
                serverData,
                false,
                transferState
        );
    }
}
