package net.aerulion.corproutine.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.utils.Inventories;
import net.aerulion.corproutine.utils.Messages;
import net.aerulion.corproutine.utils.RoutineTask;
import net.aerulion.corproutine.utils.Util;
import net.aerulion.nucleus.api.console.ConsoleUtils;
import net.aerulion.nucleus.api.mysql.MySQLUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LoadDataTask extends BukkitRunnable {

  private Player player;

  public LoadDataTask() {
    this.runTaskAsynchronously(Main.plugin);
  }

  public LoadDataTask(Player player) {
    this.player = player;
    this.runTaskAsynchronously(Main.plugin);
  }

  @Override
  public void run() {
    try (Connection connection = MySQLUtils.getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(
          "SELECT * FROM `stafftool`.`tool_routine`");
      ResultSet resultSet = preparedStatement.executeQuery();
      Main.ROUTINE_TASKS.clear();
      while (resultSet.next()) {
        Main.ROUTINE_TASKS.put(resultSet.getInt(1),
            new RoutineTask(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(3),
                Util.convertDate(resultSet.getString(5)), resultSet.getString(7),
                resultSet.getString(2), Util.convertNames(resultSet.getString(6))));
      }
        if (player != null) {
            Bukkit.getScheduler()
                .runTask(Main.plugin, () -> player.openInventory(Inventories.MainMenu()));
        }
    } catch (SQLException exception) {
      ConsoleUtils.sendColoredConsoleMessage(Messages.ERROR_DATABASE.get());
    }
  }
}