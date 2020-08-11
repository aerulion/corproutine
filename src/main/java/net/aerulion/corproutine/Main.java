package net.aerulion.corproutine;

import net.aerulion.corproutine.cmd.CMD_routine;
import net.aerulion.corproutine.listener.onInvClick;
import net.aerulion.corproutine.listener.onJoinQuit;
import net.aerulion.corproutine.utils.EditSession;
import net.aerulion.corproutine.utils.MySQL;
import net.aerulion.corproutine.utils.Routineaufgabe;
import net.aerulion.corproutine.utils.Util;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

    public static HashMap<Integer, Routineaufgabe> Routineaufgaben = new HashMap<>();
    public static HashMap<UUID, EditSession> EditSessions = new HashMap<>();
    public static List<String> Staffler = Arrays.asList("LottaXL", "TheoRetisch1", "aerulion", "kikelkik", "Richie932", "melly061992", "PixelWorker", "Himmi_MK", "Lele", "__Lemming__", "BarSerkr", "LauCherry");
    public static MySQL MySQL = null;
    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("routine").setExecutor(new CMD_routine());
        getServer().getPluginManager().registerEvents(new onInvClick(), this);
        getServer().getPluginManager().registerEvents(new onJoinQuit(), this);

        MySQL = new MySQL("localhost", "3306", "stafftool", "stafftool", "5cVkLk4WCvSaU7Bd");
        try {
            Util.loadAllData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        MySQL.close();
    }
}