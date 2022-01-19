package net.aerulion.corproutine.listener;

import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.task.LoadDataTask;
import net.aerulion.corproutine.utils.Permissions;
import net.aerulion.corproutine.utils.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class onJoinQuit implements Listener {

  @EventHandler
  public void onPlayerJoin(final @NotNull PlayerJoinEvent e) {
    if (e.getPlayer().hasPermission(Permissions.ALERT.get())) {
      new LoadDataTask();
      Util.playAlert(e.getPlayer());
    }
  }

  @EventHandler
  public void onPlayerQuit(final @NotNull PlayerQuitEvent e) {
    Main.EDIT_SESSIONS.remove(e.getPlayer().getUniqueId());
  }
}