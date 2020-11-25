package net.aerulion.corproutine;

import net.aerulion.corproutine.cmd.CMD_routine;
import net.aerulion.corproutine.listener.onInvClick;
import net.aerulion.corproutine.listener.onJoinQuit;
import net.aerulion.corproutine.task.LoadDataTask;
import net.aerulion.corproutine.utils.EditSession;
import net.aerulion.corproutine.utils.RoutineTask;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

    public static final HashMap<Integer, RoutineTask> ROUTINE_TASKS = new HashMap<>();
    public static final HashMap<UUID, EditSession> EDIT_SESSIONS = new HashMap<>();
    public static final List<String> Staffler = Arrays.asList("LottaXL", "TheoRetisch1", "aerulion", "kikelkik", "Richie932", "melly061992", "PixelWorker", "Himmi_MK", "Lele", "__Lemming__", "BarSerkr", "LauCherry");
    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        new LoadDataTask();
        getCommand("routine").setExecutor(new CMD_routine());
        getServer().getPluginManager().registerEvents(new onInvClick(), this);
        getServer().getPluginManager().registerEvents(new onJoinQuit(), this);
    }

    @Override
    public void onDisable() {
    }
}