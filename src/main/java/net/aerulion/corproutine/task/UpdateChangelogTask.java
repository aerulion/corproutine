package net.aerulion.corproutine.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.utils.Messages;
import net.aerulion.nucleus.api.console.ConsoleUtils;
import net.aerulion.nucleus.api.mysql.MySQLUtils;
import org.bukkit.scheduler.BukkitRunnable;

public class UpdateChangelogTask extends BukkitRunnable {

  private final int ID;
  private final String USER;
  private final String TASK;

  public UpdateChangelogTask(final int ID, final String USER, final String TASK) {
    this.ID = ID;
    this.USER = USER;
    this.TASK = TASK;
    this.runTaskAsynchronously(Main.plugin);
  }

  @Override
  public void run() {
    try (final Connection connection = MySQLUtils.getConnection()) {
      final PreparedStatement preparedStatement = connection.prepareStatement(
          "INSERT INTO `stafftool`.`tool_changelog` (toolname,id_intool,type,changeby,meta) VALUES (?,?,?,(SELECT uid FROM tool_users WHERE username=?),?)");
      preparedStatement.setString(1, "routinearbeiten");
      preparedStatement.setInt(2, ID);
      preparedStatement.setString(3, "update");
      preparedStatement.setString(4, USER);
      preparedStatement.setString(5, "{\"lang_args\":[\"" + USER + "\",\"" + TASK + "\"]}");
      preparedStatement.executeUpdate();
      preparedStatement.close();
      final PreparedStatement preparedStatement2 = connection.prepareStatement(
          "UPDATE `stafftool`.`tool_datacache` SET cache = (SELECT CONCAT('[', GROUP_CONCAT(JSON_OBJECT('id', id, 'task', task, 'category', category, 'nextdate', nextdate) separator ','), ']') FROM `stafftool`.`tool_routine` WHERE nextdate <= UNIX_TIMESTAMP()) WHERE title = 'routine_urgent'");
      preparedStatement2.executeUpdate();
      preparedStatement2.close();
    } catch (final SQLException exception) {
      ConsoleUtils.sendColoredConsoleMessage(Messages.ERROR_DATABASE.get());
    }
  }
}