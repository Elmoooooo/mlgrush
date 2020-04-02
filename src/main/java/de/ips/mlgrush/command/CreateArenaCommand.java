package de.ips.mlgrush.command;

import de.ips.mlgrush.MLGRushPlugin;
import de.ips.mlgrush.state.CreateMapPlayerState;
import de.ips.mlgrush.state.PlayerState;
import lombok.NoArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@NoArgsConstructor(staticName = "newInstance")
public class CreateArenaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String usedAlias, String[] args) {
        if (!(sender instanceof Player) || !sender.hasPermission(MLGRushPlugin.getInstance().getGeneralConfig()
                .getMiscData().getCreateArenaPermission()))
            return true;

        Player executor = (Player) sender;
        CreateMapPlayerState createMapPlayerState = CreateMapPlayerState.of(executor);
        PlayerState.Helper.set(executor, createMapPlayerState);

        executor.sendMessage(MLGRushPlugin.getInstance().getGeneralConfig().getMessageData().getSetUpStarted());
        executor.sendMessage(MLGRushPlugin.getInstance().getGeneralConfig().getMessageData().getFirstLocation());
        return true;
    }
}
