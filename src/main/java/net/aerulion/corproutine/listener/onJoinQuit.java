package net.aerulion.corproutine.listener;

import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.utils.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class onJoinQuit implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (e.getPlayer().hasPermission("corproutine.alert")) {
            if (Util.countExpired() > 0) {
                Util.playAlert(e.getPlayer());
            }
        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Main.EditSessions.remove(e.getPlayer().getUniqueId());
    }
}