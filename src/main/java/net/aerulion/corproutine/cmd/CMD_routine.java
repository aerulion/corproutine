package net.aerulion.corproutine.cmd;

import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.utils.Inventories;
import net.aerulion.corproutine.utils.Messages;
import net.aerulion.corproutine.utils.Permissions;
import net.aerulion.corproutine.utils.Util;
import net.aerulion.nucleus.api.sound.SoundType;
import net.aerulion.nucleus.api.sound.SoundUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class CMD_routine implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Messages.ERROR_NO_PLAYER.get());
            return true;
        }
        Player player = (Player) commandSender;
        if (!player.hasPermission(Permissions.CMD_ROUTINE.get())) {
            commandSender.sendMessage(Messages.ERROR_NO_PERMISSION.get());
            SoundUtils.playSound(player, SoundType.ERROR);
            return true;
        }

        if (Main.EDIT_SESSIONS.containsKey(player.getUniqueId())) {
            player.openInventory(Inventories.EditMenu(player.getUniqueId()));
            Util.setPlayerHeadTexturesAsync(player);
            return true;
        }
        player.openInventory(Inventories.MainMenu());
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }
}