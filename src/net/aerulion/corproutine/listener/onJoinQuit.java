package net.aerulion.corproutine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.utils.Util;

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
		if (Main.EditSessions.containsKey(e.getPlayer().getUniqueId()))
			Main.EditSessions.remove(e.getPlayer().getUniqueId());
	}
}