package net.aerulion.corproutine.listener;

import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.task.LoadDataTask;
import net.aerulion.corproutine.utils.Permissions;
import net.aerulion.corproutine.utils.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class onJoinQuit implements Listener {

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    if (e.getPlayer().hasPermission(Permissions.ALERT.get())) {
      new LoadDataTask();
      Util.playAlert(e.getPlayer());
    }
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent e) {
    Main.EDIT_SESSIONS.remove(e.getPlayer().getUniqueId());
  }
}