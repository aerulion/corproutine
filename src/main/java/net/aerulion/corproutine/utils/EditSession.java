package net.aerulion.corproutine.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import net.aerulion.corproutine.Main;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

@Getter
public class EditSession {

  private final UUID sessionOwner;
  private final int routineID;
  private final @NotNull List<String> doneBy;
  @Setter
  private String comment;
  private String nextDate;

  public EditSession(final UUID SessionOwner, final int RoutineID) {
    this.sessionOwner = SessionOwner;
    this.routineID = RoutineID;
    this.comment = "-";
    this.doneBy = new ArrayList<>();
    this.doneBy.add(Bukkit.getOfflinePlayer(this.sessionOwner).getName());
    calculateNextDate();
  }

  private void calculateNextDate() {
    final RoutineTask routineTask = Main.ROUTINE_TASKS.get(routineID);
    try {
      final long CycleLong = Long.parseLong(routineTask.getCycle());
      final @NotNull LocalDate localdate = LocalDate.now(ZoneId.systemDefault())
          .plusDays(CycleLong);
      this.nextDate = String.valueOf(
          localdate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
    } catch (final NumberFormatException e) {
      if (routineTask.getCycle().equalsIgnoreCase("@lastdayofmonth")) {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault())
            .with(TemporalAdjusters.lastDayOfMonth());
        if (localDate.equals(LocalDate.now(ZoneId.systemDefault()))) {
          localDate = YearMonth.from(localDate.plusMonths(1L)).atEndOfMonth();
        }
        this.nextDate = String.valueOf(
            localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
      }
      if (routineTask.getCycle().equalsIgnoreCase("@mondaybefore_thirdsundayofmonth")) {
        final LocalDate localdate = LocalDate.now(ZoneId.systemDefault())
            .with(TemporalAdjusters.firstDayOfNextMonth())
            .with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
            .with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
            .with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
            .with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        this.nextDate = String.valueOf(
            localdate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
      }
      if (routineTask.getCycle().equalsIgnoreCase("@mondaybefore_firstsundayofmonth")) {
        final LocalDate localdate = LocalDate.now(ZoneId.systemDefault())
            .with(TemporalAdjusters.firstDayOfNextMonth())
            .with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY))
            .with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        this.nextDate = String.valueOf(
            localdate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
      }
      if (routineTask.getCycle().equalsIgnoreCase("@sunday")) {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        if (localDate.getDayOfWeek() == DayOfWeek.MONDAY) {
          localDate = localDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        } else {
          localDate = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
              .with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        }
        this.nextDate = String.valueOf(
            localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
      }
    }
  }

  public void toggleStaffler(final String stafflerName) {
    if (!doneBy.remove(stafflerName)) {
      doneBy.add(stafflerName);
    }
  }
}