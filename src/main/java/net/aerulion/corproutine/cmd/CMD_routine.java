package net.aerulion.corproutine.cmd;

import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.utils.Inventories;
import net.aerulion.corproutine.utils.Util;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CMD_routine implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Util.PluginChatPrefix + "§cFehler: Dieser Befehl kann nur als Spieler ausgeführt werden.");
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("corproutine.use")) {
            sender.sendMessage(Util.PluginChatPrefix + "§cFehler: Du hast nicht genügend Rechte.");
            p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1F, 1F);
            return true;
        }
        try {
            Util.loadAllData();
        } catch (SQLException e) {
            p.sendMessage(Util.PluginChatPrefix + "§cFehler: Die Datenbank konnte nicht geladen werden.");
            p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1F, 1F);
            return true;
        }

        if (Main.EditSessions.keySet().contains(p.getUniqueId())) {
            p.openInventory(Inventories.EditMenu(p.getUniqueId()));
            Util.setPlayerHeadTexturesAsync(p);
            return true;
        }
        p.openInventory(Inventories.MainMenu());
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }
}