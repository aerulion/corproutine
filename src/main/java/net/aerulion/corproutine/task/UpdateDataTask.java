package net.aerulion.corproutine.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.utils.EditSession;
import net.aerulion.corproutine.utils.Messages;
import net.aerulion.nucleus.api.console.ConsoleUtils;
import net.aerulion.nucleus.api.mysql.MySQLUtils;
import net.aerulion.nucleus.api.sound.SoundType;
import net.aerulion.nucleus.api.sound.SoundUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

public class UpdateDataTask extends BukkitRunnable {

  private final EditSession EDIT_SESSION;

  public UpdateDataTask(final EditSession editSession) {
    this.EDIT_SESSION = editSession;
    this.runTaskAsynchronously(Main.plugin);
  }

  @Override
  public void run() {
    try (final Connection connection = MySQLUtils.getConnection()) {
      final PreparedStatement preparedStatement = connection.prepareStatement(
          "UPDATE `stafftool`.`tool_routine` SET nextdate=?,done_by=?,comment=? WHERE id=?");
      preparedStatement.setString(1, EDIT_SESSION.getNextDate());
      preparedStatement.setString(2, String.join(", ", EDIT_SESSION.getDoneBy()));
      preparedStatement.setString(3, EDIT_SESSION.getComment());
      preparedStatement.setInt(4, EDIT_SESSION.getRoutineID());
      preparedStatement.executeUpdate();
      preparedStatement.close();
      new UpdateChangelogTask(EDIT_SESSION.getRoutineID(),
          Bukkit.getOfflinePlayer(EDIT_SESSION.getSessionOwner()).getName(),
          Main.ROUTINE_TASKS.get(EDIT_SESSION.getRoutineID()).getName());
    } catch (final SQLException exception) {
      ConsoleUtils.sendColoredConsoleMessage(Messages.ERROR_DATABASE.get());
      final @Nullable Player player = Bukkit.getPlayer(EDIT_SESSION.getSessionOwner());
      if (player != null) {
        player.sendMessage(Messages.ERROR_DATABASE.get());
        SoundUtils.playSound(player, SoundType.ERROR);
      }
    }
  }
}