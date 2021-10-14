package net.aerulion.corproutine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.aerulion.corproutine.cmd.CMD_routine;
import net.aerulion.corproutine.listener.onInvClick;
import net.aerulion.corproutine.listener.onJoinQuit;
import net.aerulion.corproutine.task.LoadDataTask;
import net.aerulion.corproutine.utils.EditSession;
import net.aerulion.corproutine.utils.RoutineTask;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  public static final Map<Integer, RoutineTask> ROUTINE_TASKS = new HashMap<>();
  public static final Map<UUID, EditSession> EDIT_SESSIONS = new HashMap<>();
  public static final List<String> staffler = Arrays.asList("Anexa", "BarSerkr", "LauCherry",
      "Lele", "LottaXL", "PixelWorker", "Richie932", "Sanjo_chan", "TheoRetisch1", "__Lemming__",
      "aerulion", "kikelkik", "zKaaneki");
  public static Main plugin;

  @Override
  public void onDisable() {
  }

  @Override
  public void onEnable() {
    plugin = this;
    new LoadDataTask();
    getCommand("routine").setExecutor(new CMD_routine());
    getServer().getPluginManager().registerEvents(new onInvClick(), this);
    getServer().getPluginManager().registerEvents(new onJoinQuit(), this);
  }
}