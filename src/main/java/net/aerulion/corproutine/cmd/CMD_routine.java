package net.aerulion.corproutine.cmd;

import java.util.Collections;
import java.util.List;
import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.task.LoadDataTask;
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
import org.jetbrains.annotations.NotNull;

public class CMD_routine implements CommandExecutor, TabCompleter {

  public boolean onCommand(final @NotNull CommandSender commandSender,
      final @NotNull Command command, final @NotNull String label, final String[] args) {
    if (!(commandSender instanceof Player)) {
      commandSender.sendMessage(Messages.ERROR_NO_PLAYER.get());
      return true;
    }
    final @NotNull Player player = (Player) commandSender;
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
    new LoadDataTask(player);
    return true;
  }

  public List<String> onTabComplete(final @NotNull CommandSender sender,
      final @NotNull Command command, final @NotNull String alias, final String[] args) {
    return Collections.emptyList();
  }
}