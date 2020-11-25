package net.aerulion.corproutine.utils;

import net.aerulion.corproutine.Main;
import org.bukkit.Bukkit;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EditSession {

    private final UUID sessionOwner;
    private final int routineID;
    private String comment;
    private final List<String> doneBy;
    private String nextDate;

    public EditSession(UUID SessionOwner, int RoutineID) {
        this.sessionOwner = SessionOwner;
        this.routineID = RoutineID;
        this.comment = "-";
        this.doneBy = new ArrayList<>();
        this.doneBy.add(Bukkit.getOfflinePlayer(this.sessionOwner).getName());
        calculateNextDate();
    }

    private void calculateNextDate() {
        RoutineTask routineTask = Main.ROUTINE_TASKS.get(routineID);
        try {
            long CycleLong = Long.parseLong(routineTask.getCycle());
            LocalDate localdate = LocalDate.now(ZoneId.systemDefault()).plusDays(CycleLong);
            this.nextDate = "" + localdate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        } catch (NumberFormatException e) {
            if (routineTask.getCycle().equalsIgnoreCase("@lastdayofmonth")) {
                LocalDate localDate = LocalDate.now(ZoneId.systemDefault()).with(TemporalAdjusters.lastDayOfMonth());
                if (localDate.equals(LocalDate.now(ZoneId.systemDefault())))
                    localDate = YearMonth.from(localDate.plusMonths(1L)).atEndOfMonth();
                this.nextDate = "" + localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
            }
            if (routineTask.getCycle().equalsIgnoreCase("@mondaybefore_thirdsundayofmonth")) {
                LocalDate localdate = LocalDate.now(ZoneId.systemDefault()).with(TemporalAdjusters.firstDayOfNextMonth()).with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
                this.nextDate = String.valueOf(localdate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
            }
            if (routineTask.getCycle().equalsIgnoreCase("@mondaybefore_firstsundayofmonth")) {
                LocalDate localdate = LocalDate.now(ZoneId.systemDefault()).with(TemporalAdjusters.firstDayOfNextMonth()).with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY)).with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
                this.nextDate = String.valueOf(localdate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
            }
            if (routineTask.getCycle().equalsIgnoreCase("@sunday")) {
                LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
                if (localDate.getDayOfWeek().equals(DayOfWeek.MONDAY))
                    localDate = localDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
                else
                    localDate = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
                this.nextDate = String.valueOf(localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
            }
        }
    }

    public UUID getSessionOwner() {
        return this.sessionOwner;
    }

    public int getRoutineID() {
        return this.routineID;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String Comment) {
        this.comment = Comment;
    }

    public List<String> getDoneBy() {
        return this.doneBy;
    }

    public void toggleStaffler(String Name) {
        if (doneBy.contains(Name))
            doneBy.remove(Name);
        else
            doneBy.add(Name);
    }

    public String getNextDate() {
        return this.nextDate;
    }
}